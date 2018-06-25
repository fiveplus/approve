// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic','ngCordova','starter.controllers', 'starter.services'])

.run(function($ionicPlatform,$rootScope,$location,$timeout,$ionicHistory,$cordovaToast,$state,$http,locals,Push) {
     
    var notificationCallback = function(data){
        console.log('received data',data);
        var notification = angular.fromJson(data);
        //app是否运行
        var isActive = notification.notification;
        if(ionic.Platform.isIOS()){
            window.alert(notification);
        }else{
            //Android
        }
     };
     
  $ionicPlatform.ready(function() {
    //$cordovaPlugin.someFunction().then(success, error);
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    // get locals ip address
                       
    var lurl = locals.get('url');
    if(lurl != undefined){
         var url = lurl+"system/tel.login.htm";
         //var data = {temptime:Math.random()};
         $http.post(url)
              .success(function(result){
                 //locals.setObject("serv",serv);
                 locals.set("url",lurl);
                 var sessionid = locals.get("sessionid");
                 if(sessionid == '' || sessionid == undefined){
                   $state.go('signin');
                 }else{
                   var url = locals.get("url");
                   var user = locals.getObject("user");
                   $http.post(url+'system/tel.login.htm',{
                        loginName:user.username,
                        password:user.password
                        }).success(function(result){
                            if(result.return_code=='000000'){
                                locals.set("sessionid",result.sessionid);
                                locals.setObject("user",user);
                                   
                                // jpush init
                                Push.init(notificationCallback);
                                   
                                $state.go('tab.dash');
                            }else if(result.return_code=='000001'){
                                dialogs.alert("请输入用户名/密码!");
                                $state.go('signin');
                            }else if(result.return_code=='000002'){
                                dialogs.alert("用户名/密码错误，请检查后重新输入!");
                                $state.go('signin');
                            }
                    });
                }
              }).error(function(result){
                 dialogs.alert("服务器连接超时，请检查服务器工作情况！");
                 $state.go('serv');
              });
                       
    }else{
        //跳转到serv设置页面
        $state.go('serv');
        
    }
    if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
    //双击退出
   $ionicPlatform.registerBackButtonAction(function(e){
     //判断哪个页面退出
    //$rootScope.backButtonPressedOnceToExit ＝ false;
    if($location.path()=='/tab/dash'||$location.path()=='/tab/account'||$location.path()=='/tab/approves'||$location.path()=='/tab/approves2'){
           if($rootScope.backButtonPressedOnceToExit){
             ionic.Platform.exitApp();
           }else{
               $rootScope.backButtonPressedOnceToExit = true;
               $cordovaToast.showShortBottom('再按一次退出系统');
               setTimeout(function(){
                    $rootScope.backButtonPressedOnceToExit = false;
                }, 2000);
           }
    }else if($ionicHistory.backView()){
       $ionicHistory.goBack();
    }else{
       $rootScope.backButtonPressedOnceToExit = true;
       $cordovaToast.showShortBottom('再按一次退出系统');
       setTimeout(function () {
          $rootScope.backButtonPressedOnceToExit = false;
       }, 2000);
    }
    e.preventDefault();
    return false;
  },101);
 });
})
.config(['$ionicConfigProvider',function($ionicConfigProvider){
    //样式设置
    $ionicConfigProvider.platform.ios.tabs.style('standard');
    $ionicConfigProvider.platform.ios.tabs.position('bottom');
    $ionicConfigProvider.platform.android.tabs.style('standard');
    $ionicConfigProvider.platform.android.tabs.position('standard');
    
    $ionicConfigProvider.platform.ios.navBar.alignTitle('center');
    $ionicConfigProvider.platform.android.navBar.alignTitle('center');
    
    $ionicConfigProvider.platform.ios.backButton.previousTitleText('').icon('ion-ios-arrow-thin-left');
    $ionicConfigProvider.platform.android.backButton.previousTitleText('Back').icon('ion-android-arrow-back');
    //$ionicConfigProvider.platform.android.backButton.previousTitleText('').icon('ion-ios-arrow-thin-left');
    
    $ionicConfigProvider.platform.ios.views.transition('ios');
    $ionicConfigProvider.platform.android.views.transition('android');
}])
.config(['$httpProvider',function($httpProvider){
     //设置跨域访问
     //$httpProvider.defaults.withCredentials = true;
     //$httpProvider.defaults.useXDomain = true;
     $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
     $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
     //禁用页面缓存
     //$httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
     //$httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
    // $httpProvider.defaults.withCredentials = true;
     //转换序列格式
     // Override $http service's default transformRequest
     $httpProvider.defaults.transformRequest = [function(data){
    /**
     * The workhorse; converts an object to x-www-form-urlencoded serialization.
     * @param {Object} obj
     * @return {String}
     */
    var param = function(obj) {
    var query = '';
    var name, value, fullSubName, subName, subValue, innerObj, i;
    
    for (name in obj) {
    value = obj[name];
    if (value instanceof Array) {
    for (i = 0; i < value.length; ++i) {
    subValue = value[i];
    fullSubName = name + '[' + i + ']';
    innerObj = {};
    innerObj[fullSubName] = subValue;
    query += param(innerObj) + '&';
    }
    } else if (value instanceof Object) {
    for (subName in value) {
    subValue = value[subName];
    fullSubName = name + '[' + subName + ']';
    innerObj = {};
    innerObj[fullSubName] = subValue;
    query += param(innerObj) + '&';
    }
    } else if (value !== undefined && value !== null) {
    query += encodeURIComponent(name) + '='
    + encodeURIComponent(value) + '&';
    }
    }
    
    return query.length ? query.substr(0, query.length - 1) : query;
    };
    
    return angular.isObject(data) && String(data) !== '[object File]'
    ? param(data)
    : data;
    
    }];

         
 }])
.config(['$provide',function($provide){
    //解决重复点击BUG
    $provide.decorator('ngClickDirective',['$delegate','$timeout',function($delegate,$timeout){
        var original = $delegate[0].compile;
        var delay = 500;
        $delegate[0].compile = function(element,attrs,transclude){
            var disabled = false;
            function onClick(evt){
                if(disabled){
                    evt.preventDefault();
                    evt.stopImmediatePropagation();
                }else{
                    disabled = true;
                    $timeout(function(){
                        disabled = false;
                    }, delay, false);
                }
            }
            element.on('click', onClick);
            return original(element, attrs, transclude);
        };
        return $delegate;
    }]);
         
}])
.config(function($stateProvider, $urlRouterProvider,$httpProvider) {
  
  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    .state('signin',{
    url:'/sign-in',
    templateUrl:'templates/sign-in.html',
    controller: 'SignInCtrl'
  })

  // setup an abstract state for the tabs directive
    .state('tab', {
    url: '/tab',
    cache:'false',
    abstract: true,
    templateUrl: 'templates/tabs.html'
  })

  // Each tab has its own nav history stack:

  .state('tab.dash', {
    url: '/dash',
    views: {
      'tab-dash': {
        templateUrl: 'templates/tab-dash.html',
        controller: 'DashCtrl'
      }
    }
  })
  .state('tab.approves', {
      url: '/approves',
      views: {
        'tab-approves': {
          templateUrl: 'templates/tab-approves.html',
          controller: 'ApprovesCtrl'
        }
      }
    })
    .state('tab.approves2',{
        url:'/approves2',
        views:{
           'tab-approves2':{
            templateUrl:'templates/tab-approves2.html',
            controller: 'ApprovesCtrl2'
           }
        }
    })
    .state('tab.approve-detail', {
      url: '/approves/:uaId',
      views: {
        'tab-approves': {
          templateUrl: 'templates/approve-detail.html',
          controller: 'ApproveDetailCtrl'
        }
      }
    })
    .state('tab.approve2-detail',{
        url:'/approves2/:logId',
        views:{
        'tab-approves2':{
            templateUrl:'templates/approve2-detail.html',
            controller:'Approve2DetailCtrl'
        }
    }
   })
   .state('tab.approvepush-detail',{
          url:'/approvepushs/:apId',
          views:{
          'tab-dash':{
            templateUrl:'templates/approvepush-detail.html',
            controller:'ApprovePushCtrl'
          }
        }
   })
  .state('tab.account', {
    url: '/account',
    views: {
      'tab-account': {
        templateUrl: 'templates/tab-account.html',
        controller: 'AccountCtrl'
      }
    }
  })
    
  .state('serv',{
         url:'/serv',
         templateUrl:'templates/serv.html',
         controller: 'ServCtrl'
  })
  .state('tab.allapproves', {
    url: '/allapproves',
    views: {
    'tab-account': {
         templateUrl: 'templates/tab-allapproves.html',
         controller: 'AllApprovesCtrl'
    }
  }
})
.state('tab.allarchives',{
    url:'/allarchives',
    views:{
    'tab-account':{
       templateUrl:'templates/tab-allarchives.html',
       controller:'AllArchivesCtrl'
    }
  }
})
.state('tab.allapprove-detail', {
    url: '/allapproves/:uaId',
    views: {
        'tab-account': {
        templateUrl: 'templates/approve-detail.html',
        controller: 'ApproveDetailCtrl'
        }
    }
}).state('tab.allarchive-detail', {
    url: '/allarchive/:alId',
    views: {
        'tab-account': {
        templateUrl: 'templates/archive-detail.html',
        controller: 'ArchiveDetailCtrl'
        }
    }
});

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/sign-in');

});


