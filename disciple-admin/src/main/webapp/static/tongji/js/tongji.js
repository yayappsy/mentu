/**
 * Created by laozh on 2017/4/6.
 */
(function () {
    var mainTool = {};

    //获取浏览器信息
    mainTool.info = {};
    mainTool.info.isIE = /msie (\d+\.\d+)/i.test(navigator.userAgent);
    mainTool.info.cookieEnabled = navigator.cookieEnabled;
    mainTool.info.javaEnabled = navigator.javaEnabled();
    mainTool.info.language = navigator.language ||
        navigator.browserLanguage ||
        navigator.systemLanguage ||
        navigator.userLanguage || "";
    mainTool.info.resolution = (window.screen.width || 0) + "x" + (window.screen.height || 0);
    mainTool.info.colorDepth = window.screen.colorDepth || 0;

    // 设置、获取cookie
    mainTool.Cookie = {};
    mainTool.Cookie.SESSION_EXPIRE = 1000 * 60 * 30;//30分钟
    mainTool.Cookie.USER_EXPIRE = 1000 * 60 * 60 * 24 * 730;//两年
    mainTool.Cookie._set = function (name, value, expires, path, domain, secure) {
        document.Cookie = name + "=" + escape(value) +
            ((expires) ? "; expires=" + expires.toGMTString() : "") +
            ((path) ? "; path=" + path : "") +
            ((domain) ? "; domain=" + domain : "") +
            ((secure) ? "; secure" : "");

    };
    mainTool.Cookie.set = function (name, value) {
        var expandDate = new Date();
        expandDate.setTime(expandDate.getTime() + USER_EXPIRE);
        var path = "/", domain = document.domain;
        mainTool.Cookie.set(name, value, expandDate, path, domain);
    };
    mainTool.Cookie.get = function (a) {
        return (a = new RegExp("(^| )" + a + "=([^;]*)(;|$)").exec(document.Cookie)) ? a[2] : null;
    };


    //封装 localStorage 实现，使用百度编辑器的实现 ueditor
    mainTool.LocalStorage = (function () {

        var storage = window.localStorage || getUserData() || null,
            LOCAL_FILE = 'localStorage';

        return {

            saveLocalData: function (key, data) {

                if (storage && data) {
                    storage.setItem(key, data);
                    return true;
                }

                return false;

            },

            getLocalData: function (key) {

                if (storage) {
                    return storage.getItem(key);
                }

                return null;

            },

            removeItem: function (key) {

                storage && storage.removeItem(key);

            }

        };

        function getUserData() {

            var container = document.createElement("div");
            container.style.display = "none";

            if (!container.addBehavior) {
                return null;
            }

            container.addBehavior("#default#userdata");

            return {

                getItem: function (key) {

                    var result = null;

                    try {
                        document.body.appendChild(container);
                        container.load(LOCAL_FILE);
                        result = container.getAttribute(key);
                        document.body.removeChild(container);
                    } catch (e) {
                    }

                    return result;

                },

                setItem: function (key, value) {

                    document.body.appendChild(container);
                    container.setAttribute(key, value);
                    container.save(LOCAL_FILE);
                    document.body.removeChild(container);

                },

                //// 暂时没有用到
                //clear: function () {
                //
                //    var expiresTime = new Date();
                //    expiresTime.setFullYear(expiresTime.getFullYear() - 1);
                //    document.body.appendChild(container);
                //    container.expires = expiresTime.toUTCString();
                //    container.save(LOCAL_FILE);
                //    document.body.removeChild(container);
                //
                //},

                removeItem: function (key) {

                    document.body.appendChild(container);
                    container.removeAttribute(key);
                    container.save(LOCAL_FILE);
                    document.body.removeChild(container);

                }

            };

        }

    })();

    //封装 sessiontorage 实现
    mainTool.SessionStorage = (function () {
        var storage = window.sessionStorage || null;
        return {
            saveSessionData: function (key, data) {

                if (storage && data) {
                    storage.setItem(key, data);
                    return true;
                }

                return false;

            },

            getSessionData: function (key) {

                if (storage) {
                    return storage.getItem(key);
                }

                return null;

            },

            removeItem: function (key) {

                storage && storage.removeItem(key);

            }
        };
    })();


    mainTool.Tools = {};
    mainTool.Tools.checkPlugins = function (pluginName) {
        var np = navigator.plugins;
        if (window.ActiveXObject) {
            // IE
            // ActiveXObject的对象名
            var activexObjectName = pluginName + "." + pluginName;
            try {
                var axobj = eval("new ActiveXObject(activexObjectName);");
                // 将对象转化为布尔类型
                return axobj ? true : false;
            } catch (e) {
                return false;
            }
        } else if (np && np.length) {
            // 非IE
            for (var i = 0; i < np.length; i++) {
                if (np[i].name.toLowerCase().indexOf(pluginName.toLowerCase()) != -1)
                    return true;
            }
            return false;
        } else {
            // 其他则返回false
            return false;
        }
    };

    mainTool.Tools.uuid = function () {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "-";

        var uuid = s.join("");
        return uuid;
    };

    mainTool.Tools.flashChecker = function () {
        var hasFlash = 0; //是否安装了flash
        var flashVersion = 0; //flash版本
        if (document.all) {
            var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
            if (swf) {
                hasFlash = 1;
                var VSwf = swf.GetVariable("$version");
                flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
            }
        } else {
            if (navigator.plugins && navigator.plugins.length > 0) {
                var swf = navigator.plugins["Shockwave Flash"];
                if (swf) {
                    hasFlash = 1;
                    var words = swf.description.split(" ");
                    for (var i = 0; i < words.length; ++i) {
                        if (isNaN(parseInt(words[i]))) continue;
                        flashVersion = parseInt(words[i]);
                    }
                }
            }
        }
        return {f: hasFlash, v: flashVersion};
    };
    
    //数据统计
    mainTool.Track = {};

    mainTool.Track.getVisitorId = function () {
        var searchStr = "visitorId";
        var value = mainTool.LocalStorage.getLocalData(searchStr);
        if (!value) {
            mainTool.Track.ifNewVisitor = 1;
            value = mainTool.Tools.uuid();
            mainTool.LocalStorage.saveLocalData(searchStr, value);
        }
        return value;
    };
    mainTool.Track.getBrowseId = function () {
        var searchStr = "browseId";
        var value = mainTool.SessionStorage.getSessionData(searchStr);
        if (!value) {
            mainTool.Track.ifNewBrowse = 1;
            value = mainTool.Tools.uuid();
            mainTool.SessionStorage.saveSessionData(searchStr, value);
        }
        return value;
    };

    mainTool.Track.getPageViewId = function () {
        return mainTool.Tools.uuid();
    };

    //URL编码
    mainTool.Tools._escape = function (str) {
        if (typeof(encodeURIComponent) == 'function') {
            return encodeURIComponent(str);
        } else {
            return escape(str);
        }
    };

    mainTool.Track.plusPvd = function () {
        var pvd = mainTool.SessionStorage.getSessionData('pvd');
        if(pvd){
            pvd++;
        }else{
            pvd=1;
        }
        mainTool.SessionStorage.saveSessionData("pvd",pvd);
        return pvd;
    };

    var visitInfo = (function () {
        function VisitInfo() {
            this.init();
        }
        var tl = mainTool.Tools,i = mainTool.info,tk = mainTool.Track ;
        VisitInfo.prototype = {
            init:function () {
                //首先生成访问者id
                this.visitorId = tk.getVisitorId();      
                //访问id
                this.browseId = tk.getBrowseId();
                //页面浏览id
                this.pageViewId = tk.getPageViewId();
                //标题
                var _title = "";
                if (document.title && document.title != "") {
                    _title = document.title;
                }
                this.title=_title;
                //链接
                this.url = document.location.href;
                //是否新的访问者
                if(tk.ifNewVisitor!=null){
                   this.ifNewVisitor=tk.ifNewVisitor;
                }
               
                //是否新的浏览
                if(tk.ifNewBrowse!=null){
                	  this.ifNewBrowse=tk.ifNewBrowse;
                 }                  
                //来源
                this.referrer = document.referrer;
                this.resolution = i.resolution;
                this.colorDepth = i.colorDepth;
                this.language = i.language;
                this.javaEnabled = i.javaEnabled ? 1 : 0;
                this.cookieEnabled = i.cookieEnabled ? 1 : 0;
                this.flashEnabled = tl.flashChecker().f;
                this.flashVersion = tl.flashChecker().v;
                this.pageViewDepth = tk.plusPvd();
            }
        };
        return new VisitInfo();
    })();


    mainTool.Tools.parseParam = function (obj) {
        return Object.keys(obj).map(function(key){
            return encodeURIComponent(key) + '=' + encodeURIComponent(obj[key]);
        }).join('&');
    };

    mainTool.VisitTrack = {};

    mainTool.VisitTrack.log = function (siteId,trackUrl) {
        visitInfo.siteId=siteId;
        VisitTrack._track_url = trackUrl;
        var e = new Image;
        e.src = VisitTrack._track_url+"?"+mainTool.Tools.parseParam(visitInfo);
        console.log(visitInfo);
        
    };

    window.VisitTrack = mainTool.VisitTrack;
})();