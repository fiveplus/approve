angular.module('starter.services', [])
.factory('Chats', function() {
  // Might use a resource here that returns a JSON array

  // Some fake testing data
  var chats = [{
    id: 0,
    name: 'Ben Sparrow',
    lastText: 'You on your way?',
    face: 'img/ben.png'
  }, {
    id: 1,
    name: 'Max Lynx',
    lastText: 'Hey, it\'s me',
    face: 'img/max.png'
  }, {
    id: 2,
    name: 'Adam Bradleyson',
    lastText: 'I should buy a boat',
    face: 'img/adam.jpg'
  }, {
    id: 3,
    name: 'Perry Governor',
    lastText: 'Look at my mukluks!',
    face: 'img/perry.png'
  }, {
    id: 4,
    name: 'Mike Harrington',
    lastText: 'This is wicked good ice cream.',
    face: 'img/mike.png'
  }];

  return {
    all: function() {
      return chats;
    },
    remove: function(chat) {
      chats.splice(chats.indexOf(chat), 1);
    },
    get: function(chatId) {
      for (var i = 0; i < chats.length; i++) {
        if (chats[i].id === parseInt(chatId)) {
          return chats[i];
        }
      }
      return null;
    }
  };
})

.factory('locals',['$window',function($window){
    return{
        //存储单个属性
        set :function(key,value){
        $window.localStorage[key]=value;
        },
        //读取单个属性
        get:function(key,defaultValue){
        return  $window.localStorage[key] || defaultValue;
        },
        
        //存储对象，以JSON格式存储
        setObject:function(key,value){
        $window.localStorage[key]=JSON.stringify(value);
        },
        //读取对象
        getObject: function (key) {
        return JSON.parse($window.localStorage[key] || '{}');
        },
        remove:function(key){
            $window.localStorage.removeItem(key);
        }
    }
        
}])
.factory('dialogs',['$ionicPopup',function($ionicPopup){
    return{
        alert:function(content){
            $ionicPopup.alert({title:'提示信息',template:content});
        }
    }
}])
.factory('Push',function(){
    var push;
    return{
         setBadge:function(badge){
            if(push){
                console.log('jpush:set badge',badge);
                plugins.jPushPlugin.setBadge(badge);
            }
         },
         setAlias:function(alias){
            if(push){
                console.log('jpush:set alias',alias);
                plugins.jPushPlugin.setAlias(alias);
            }
         },
         check: function() {
            if (window.jpush && push) {
                plugins.jPushPlugin.receiveNotificationIniOSCallback(window.jpush);
                window.jpush = null;
            }
         },
         init: function(notificationCallback) {
            console.log('jpush: start init-----------------------');
            push = window.plugins && window.plugins.jPushPlugin;
            if (push) {
                console.log('jpush: init');
                plugins.jPushPlugin.init();
                plugins.jPushPlugin.setDebugMode(true);
                plugins.jPushPlugin.openNotificationInAndroidCallback = notificationCallback;
                plugins.jPushPlugin.receiveNotificationIniOSCallback = notificationCallback;
            }
         
         }
    };
});
