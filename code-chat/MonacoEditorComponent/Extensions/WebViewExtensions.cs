using Monaco.Helpers;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using System;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Runtime.Serialization;
using System.Threading.Tasks;
using Windows.Data.Json;
using Windows.UI.Xaml.Controls;

namespace Monaco.Extensions
{
    internal static class WebViewExtensions
    {
        public static async Task RunScriptAsync(
            this WebView _view,
            string script,
            [CallerMemberName] string member = null,
            [CallerFilePath] string file = null,
            [CallerLineNumber] int line = 0)
        {
            await _view.RunScriptAsync<object>(script, member, file, line);
        }

        public static async Task<T> RunScriptAsync<T>(
            this WebView _view, 
            string script, 
            [CallerMemberName] string member = null,
            [CallerFilePath] string file = null,
            [CallerLineNumber] int line = 0)
        {
            var start = "try {\n";
            if (typeof(T) != typeof(object))
            {
                script = script.Trim(';');
                start += "JSON.stringify(" + script + ");";
            }
            else
            {
                start += script;
            }
            var fullscript = start + 
                "\n} catch (err) { JSON.stringify({ wv_internal_error: true, message: err.message, description: err.description, number: err.number, stack: err.stack }); }";

            if (_view.Dispatcher.HasThreadAccess)
            {
                try
                {
                    return await RunScriptHelperAsync<T>(_view, fullscript);
                }
                catch (Exception e)
                {
                    throw new JavaScriptExecutionException(member, file, line, script, e);
                }
            }
            else
            {
                return await _view.Dispatcher.RunTaskAsync(async () =>
                {
                    try
                    {
                        return await RunScriptHelperAsync<T>(_view, fullscript);
                    }
                    catch (Exception e)
                    {
                        throw new JavaScriptExecutionException(member, file, line, script, e);
                    }
                });
            }
        }

        private static async Task<T> RunScriptHelperAsync<T>(WebView _view, string script)
        {            
            var returnstring = await _view.InvokeScriptAsync("eval", new string[] { script });

            if (JsonObject.TryParse(returnstring, out JsonObject result))
            {
                if (result.ContainsKey("wv_internal_error") && result["wv_internal_error"].ValueType == JsonValueType.Boolean && result["wv_internal_error"].GetBoolean())
                {
                    throw new JavaScriptInnerException(result["message"].GetString(), result["stack"].GetString());
                }
            }

            if (returnstring != null && returnstring != "null")
            {
                return JsonConvert.DeserializeObject<T>(returnstring);
            }

            return default(T);
        }

        private static JsonSerializerSettings _settings = new JsonSerializerSettings()
        {
            NullValueHandling = NullValueHandling.Ignore,
            ContractResolver = new CamelCasePropertyNamesContractResolver()
        };

        public static async Task InvokeScriptAsync(
            this WebView _view,
            string method,
            object arg,
            bool serialize = true,
            [CallerMemberName] string member = null,
            [CallerFilePath] string file = null,
            [CallerLineNumber] int line = 0)
        {
            await _view.InvokeScriptAsync<object>(method, arg, serialize, member, file, line);
        }

        public static async Task InvokeScriptAsync(
            this WebView _view,
            string method,
            object[] args,
            bool serialize = true,
            [CallerMemberName] string member = null,
            [CallerFilePath] string file = null,
            [CallerLineNumber] int line = 0)
        {
            await _view.InvokeScriptAsync<object>(method, args, serialize, member, file, line);
        }

        public static async Task<T> InvokeScriptAsync<T>(
            this WebView _view,
            string method,
            object arg,
            bool serialize = true,
            [CallerMemberName] string member = null,
            [CallerFilePath] string file = null,
            [CallerLineNumber] int line = 0)
        {
            return await _view.InvokeScriptAsync<T>(method, new object[] { arg }, serialize, member, file, line);
        }

        public static async Task<T> InvokeScriptAsync<T>(
            this WebView _view,
            string method,
            object[] args,
            bool serialize = true,
            [CallerMemberName] string member = null,
            [CallerFilePath] string file = null,
            [CallerLineNumber] int line = 0)
        {
            string[] sanitizedargs;

            if (serialize)
            {
                sanitizedargs = args.Select(item =>
                {
                    if (item is int || item is double)
                    {
                        return item.ToString();
                    }
                    else if (item is string)
                    {
                        return JsonConvert.ToString(item);
                    }
                    else
                    {
                        // TODO: Need JSON.parse?
                        return JsonConvert.SerializeObject(item, _settings);
                    }
                }).ToArray();
            }
            else
            {
                sanitizedargs = args.Select(item => item.ToString()).ToArray();
            }

            var script = method + "(" + string.Join(",", sanitizedargs) + ");";

            return await RunScriptAsync<T>(_view, script, member, file, line);
        }
    }

    internal sealed class JavaScriptExecutionException : Exception
    {
        public string Script { get; private set; }

        public string Member { get; private set; }

        public string FileName { get; private set; }

        public int LineNumber { get; private set; }

        public JavaScriptExecutionException(string member, string filename, int line, string script, Exception inner)
            : base("Error Executing JavaScript Code for " + member + "\nLine " + line + " of " + filename + "\n" + script + "\n", inner)
        {
            this.Member = member;
            this.FileName = filename;
            this.LineNumber = line;
            this.Script = script;
        }
    }

    internal sealed class JavaScriptInnerException : Exception
    {
        public string JavaScriptStackTrace { get; private set; } // TODO Use Enum of JS error types https://www.w3schools.com/js/js_errors.asp

        public JavaScriptInnerException(string message, string stack)
            : base(message)
        {
            this.JavaScriptStackTrace = stack;
        }
    }
}
