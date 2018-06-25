angular.module('starter.controllers', [])

.controller('DashCtrl', function($scope,$http,locals,$cordovaToast,$ionicScrollDelegate) {
    $scope.hasmore = true;
    $scope.loading = false;
    var run = false;
    var page = 1;
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
    $http.post(url+'system/tel.user.htm;jsessionid='+sessionid)
    .success(function(result){
        $scope.user = result.user;
    });
    $http.post(url+'userapprove/tel.add_list.htm;jsessionid='+sessionid)
    .success(function(result){
        page = 1;
        $scope.approves = result.approves;
        //$scope.acount = result.acount;
        $scope.wcount = result.wcount;
        $scope.remove = function(ap){
            $scope.approves.splice($scope.approves.indexOf(ap), 1);
        }
    });
    $scope.loadlist = function(){
        page = 1;
        var url = locals.get('url');
        var sessionid = locals.get('sessionid');
        var searchContent = $scope.searchContent;
        var data = {name:searchContent};
        $http.post(url+'system/tel.user.htm;jsessionid='+sessionid)
        .success(function(result){
             $scope.user = result.user;
         });
                
                
        $http.post(url+'userapprove/tel.add_list.htm;jsessionid='+sessionid,data)
        .success(function(result){
             $scope.approves = result.approves;
             //$scope.acount = result.acount;
             $scope.wcount = result.wcount;
             $scope.$broadcast('scroll.infiniteScrollComplete');
             $scope.$broadcast('scroll.refreshComplete');
             $scope.remove = function(ap){
                $scope.approves.splice($scope.approves.indexOf(ap), 1);
             }
         });

    };
    
    $scope.search = function(searchContent){
            page = 1;
            var data = {name:searchContent};
            $http.post(url+'userapprove/tel.add_list.htm;jsessionid='+sessionid,data)
            .success(function(result){
                 $scope.searchContent = searchContent;
                 $scope.approves = result.approves;
                 //$scope.acount = result.acount;
                 $scope.wcount = result.wcount;
                 $scope.$broadcast('scroll.infiniteScrollComplete');
                 $scope.$broadcast('scroll.refreshComplete');
                 $scope.remove = function(ap){
                     $scope.approves.splice($scope.approves.indexOf(ap), 1);
                 }
                 $ionicScrollDelegate.scrollTop();
            });
    };
            
    $scope.loadOldlist = function(){
        var old = $scope.approves;
        if(old!=undefined){
            load(3);
        }else{
            $scope.$broadcast('scroll.infiniteScrollComplete');
        }
        
    };
    
 
    /* state:1 初始化 2刷新 3加载更多 */
    function load(state){
        if(!run){
            if($scope.approves.length<10){
                $scope.$broadcast('scroll.infiniteScrollComplete');
                return;
            }
            $scope.loading = true;
            run = true;
            var searchContent = $scope.searchContent;
            var data = {name:searchContent,page:page};
            $http.post(url+'userapprove/tel.add_list.htm;jsessionid='+sessionid,data)
            .success(function(result){
                 run = false;
                 $scope.loading = false;
                 if(state==3){
                    $scope.searchContent = searchContent;
                    var oldid = $scope.approves[$scope.approves.length-1].id;
                    var newid = result.approves[result.approves.length-1].id;
                     
                    $scope.approves = $scope.approves.concat(result.approves);
                    page +=1;
                 }
                 $scope.$broadcast('scroll.infiniteScrollComplete');
                 
                     
                 $scope.remove = function(ap){
                    $scope.approves.splice($scope.approves.indexOf(ap), 1);
                 };
            }).error(function(result){
                run = false;
                $scope.loading = false;
                $scope.$broadcast('scroll.infiniteScrollComplete');
                 $cordovaToast.showShortBottom('数据加载错误，请重新加载.');
            });
        }
    }
            

})

.controller('ApprovesCtrl', function($scope,$http,locals) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});
    $scope.iloading = false;
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
            
    $http.post(url+'userapprove/tel.list.htm;jsessionid='+sessionid)
        .success(function(result){
            $scope.iloading = true;
            $scope.userapproves = result.userApproves;
            $scope.remove = function(ua){
                 $scope.userapproves.splice($scope.userapproves.indexOf(ua), 1);
            };
        });
    $scope.loadlist = function(){
        var url = locals.get('url');
        var sessionid = locals.get('sessionid');
        $http.post(url+'userapprove/tel.list.htm;jsessionid='+sessionid)
        .success(function(result){
             $scope.userapproves = result.userApproves;
             $scope.$broadcast('scroll.refreshComplete');
             $scope.remove = function(ua){
                 $scope.userapproves.splice($scope.userapproves.indexOf(ua), 1);
             };
        });
    
    };
           
  //$scope.chats = Chats.all();
  //$scope.remove = function(chat) {
  //  Chats.remove(chat);
  //};
})

.controller('ApprovesCtrl2',function($scope,$http,locals){
    $scope.iloading = false;
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
    $http.post(url+'userapprove/tel.user_approves.htm;jsessionid='+sessionid)
    .success(function(result){
        $scope.iloading = true;
        $scope.logs = result.logs;
        $scope.remove = function(log){
            $scope.logs.splice($scope.logs.indexOf(log), 1);
        }
    });
    $scope.loadlist = function(){
        var url = locals.get('url');
        var sessionid = locals.get('sessionid');
        $http.post(url+'userapprove/tel.user_approves.htm;jsessionid='+sessionid)
        .success(function(result){
            $scope.logs = result.logs;
            $scope.$broadcast('scroll.refreshComplete');
            $scope.remove = function(log){
                 $scope.logs.splice($scope.logs.indexOf(log), 1);
            }
        });
    
    };
            
})

.controller('ApproveDetailCtrl', function($scope, $stateParams, $http,locals) {
    $scope.loading = false;
    var uaid = $stateParams.uaId;
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
    $http.post(url+'userapprove/tel.approvelog.htm;jsessionid='+sessionid,{id:uaid})
    .success(function(result){
        $scope.loading = true;
             
        $scope.list = result.list;
        
        if(result.list != null && result.list.length > 0){
             $scope.ua = result.list[0].ua;
        }
    });
})
.controller('Approve2DetailCtrl',function($scope,$stateParams,$http,$state,locals,dialogs){
    $scope.loading = false;
    var logid = $stateParams.logId;
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
    $http.post(url+'userapprove/tel.approve_updateInit.htm;jsessionid='+sessionid,{id:logid})
    .success(function(result){
        $scope.loading = true;
        $scope.lo = result.log;
        $scope.oldlogs = result.oldlogs;
    });
            
    $scope.yesno = [{name:'Y',value:'是'},{name:'N',value:'否'}];
    $scope.log = {};
            
    $scope.submit = function (obj){
       document.getElementById(obj).reset();
       if($scope.log.status == 'W' || $scope.log.status == null){
        dialogs.alert("请选择是否同意！");
        return;
    }
            
    $scope.log.id = $scope.lo.id;

    $http.post(url+'userapprove/tel.approve_update.htm;jsessionid='+sessionid,$.param($scope.log))
    .success(function(result){
         if(result.return_code == '000000'){
            dialogs.alert("审批提交成功！");
            $state.go("tab.approves2");
         }else if(result.return_code=='001001'){
            dialogs.alert("请先设置分管高层！");
         }else if(result.return_code == '001002'){
            dialogs.alert("审批提交失败！");
         }else if(result.return_code == '001003'){
            dialogs.alert("系统未知错误！");
         }
    });
            
            
            
    };

})

.controller('AccountCtrl',function($scope,$state,$http,locals) {
  //$scope.settings = {
  //  enableFriends: true
  //};
  $scope.signOut = function(){
    locals.remove('sessionid');
    locals.remove('user');
     $state.go('signin');
  }
            
            
  var url = locals.get('url');
  var sessionid = locals.get('sessionid');
    
  $http.post(url+'system/tel.user.htm;jsessionid='+sessionid)
  .success(function(result){
    $scope.user = result.user;
  });
            
$scope.loadlist = function(){
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
    $http.post(url+'system/tel.user.htm;jsessionid='+sessionid)
    .success(function(result){
        $scope.user = result.user;
        $scope.$broadcast('scroll.refreshComplete');
    });

};

            
})

.controller('SignInCtrl',function($scope,$state,$http,locals,dialogs){
            
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
            
    $scope.signIn = function(user){
        var url = locals.get("url");
        $http.post(url+'system/tel.login.htm',{
            loginName:user.username,
            password:user.password
        }).success(function(result){
            if(result.return_code=='000000'){
                locals.set("sessionid",result.sessionid);
                locals.setObject("user",user);
                
                // jpush init
                Push.init(notificationCallback);
                //setAlias
                //区分大小写(覆盖逻辑)
                Push.setAlias(user.username.toLocaleLowerCase());


                $state.go('tab.dash');
           }else if(result.return_code=='000001'){
                dialogs.alert("请输入用户名/密码!");
           }else if(result.return_code=='000002'){
                dialogs.alert("用户名/密码错误，请检查后重新输入!");
           }
           
           
        });
        
        
    };
})

.controller('ServCtrl',function($scope,$state,$http,locals,dialogs){
    $scope.saveLocal = function(serv){
        serv.address = serv.address=="/"?serv.address:serv.address+"/";
        var url = "http://"+serv.ip+":"+serv.port+serv.address+"system/tel.login.htm";
        //var data = {temptime:Math.random()};
        $http.post(url).success(function(result){
             //locals.setObject("serv",serv);
             locals.set("url","http://"+serv.ip+":"+serv.port+serv.address);
             dialogs.alert("服务器设置成功！");
             $state.go('signin');
        }).error(function(result){
             dialogs.alert("服务器连接超时，请检查服务器工作情况！");
        });
    };
})
.controller('ApprovePushCtrl',function($scope,$state,$stateParams,$http,locals,dialogs){
    var apid = $stateParams.apId;
           
    var sessionid = locals.get("sessionid");
    var url = locals.get("url");
    $http.post(url+'userapprove/tel.addinit.htm;jsessionid='+sessionid,{id:apid})
    .success(function(result){
        $scope.approve = result.approve;
        $scope.userapprove = {};
        $scope.userapprove.id = $scope.approve.id;
    });
    
            
    $scope.submit = function(obj){
        document.getElementById(obj).reset();
        $http.post(url+'userapprove/tel.add.htm;jsessionid='+sessionid,$.param($scope.userapprove))
            .success(function(result){
                if(result.return_code=='000000'){
                     //document.getElementById(obj).reset();
                     dialogs.alert("审批提交成功！");
                     $state.go("tab.dash");
                 }else if(result.return_code=='002001'){
                    dialogs.alert("请输入审批标题");
                 }else if(result.return_code=='002002'){
                    dialogs.alert("请先创建该部门部门主任");
                 }else if(result.return_code=='002003'){
                    dialogs.alert("未知错误!");
                 }
            
            });
         
    };
})
.controller('AllArchivesCtrl',function($scope,$http,locals,$cordovaToast,$ionicScrollDelegate){
    $scope.hasmore = true;
    $scope.loading = false;
    var run = false;
    var page = 1;
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
    $http.post(url+'archive/tel.all_archives.htm;jsessionid='+sessionid)
    .success(function(result){
        $scope.archives = result.archives;
        $scope.remove = function(ar){
             $scope.archives.splice($scope.archives.indexOf(ar),1);
        }
    });
            
    $scope.loadlist = function(){
        page = 1;
        var url = locals.get('url');
        var sessionid = locals.get('sessionid');
        $http.post(url+'archive/tel.all_archives.htm;jsessionid'+sessionid)
        .success(function(result){
            $scope.archives = result.archives;
            $scope.$broadcast('scroll.refreshComplete');
            $scope.remove = function(ar){
                $scope.archives.splice($scope.archives.indexOf(ar),1);
            }
        });
    };
    
    $scope.loadOldlist = function(){
        var old = $scope.archives;
        if(old != undefined){
            load(3);
        }else{
            $scope.$broadcast('scroll.infiniteScrollComplete');
        }
    };
            
    /* state:1 初始化 2刷新 3加载更多 */
    function load(state){
        if(!run){
            $scope.loading = true;
            run = true;
            var data = {page:page};
            $http.post(url+'archive/tel.all_archives.htm;jessionid='+sessionid,data)
            .success(function(result){
                run = false;
                $scope.loading = false;
                if(state==3){
                     var oldid = $scope.archives[$scope.archives.length-1].id;
                     var newid = result.archives[result.archives.length-1].id;
                     $scope.archives = $scope.archives.concat(result.archives);
                     page+=1;
                }
                $scope.$broadcast('scroll.infiniteScrollComplete');
                $scope.remove = function(ar){
                     $scope.archives.splice($scope.archives.indexOf(ar),1);
                };
                     
            }).error(function(result){
                     run = false;
                     $scope.loading = false;
                $scope.$broadcast('scroll.infiniteScrollComplete');
                $cordovaToast.showShortBottom('数据加载错误，请重新加载.');
            });
            
        }
    }
            
})
.controller('AllApprovesCtrl',function($scope,$http,locals,$cordovaToast,$ionicScrollDelegate){
    $scope.hasmore = true;
    $scope.loading = false;
    var run = false;
    var page = 1;
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
    $http.post(url+'userapprove/tel.list_all.htm;jsessionid='+sessionid)
    .success(function(result){
        $scope.userapproves = result.userApproves;
        $scope.remove = function(ua){
            $scope.userapproves.splice($scope.userapproves.indexOf(ua),1);
        }
    });
             
    $scope.loadlist = function(){
        page = 1;
        var url = locals.get('url');
        var sessionid = locals.get('sessionid');
        $http.post(url+'userapprove/tel.list_all.htm;jsessionid'+sessionid)
        .success(function(result){
            $scope.userapproves = result.userApproves;
            $scope.$broadcast('scroll.refreshComplete');
            $scope.remove = function(ua){
                $scope.userapproves.splice($scope.userapproves.indexOf(ua),1);
            }
        });
    };
             
    $scope.loadOldlist = function(){
        var old = $scope.userapproves;
        if(old != undefined){
             load(3);
        }else{
             $scope.$broadcast('scroll.infiniteScrollComplete');
        }
    };
             
    /* state:1 初始化 2刷新 3加载更多 */
    function load(state){
        if(!run){
             $scope.loading = true;
             run = true;
             var data = {page:page};
             $http.post(url+'userapprove/tel.list_all.htm;jessionid='+sessionid,data)
             .success(function(result){
                run = false;
                $scope.loading = false;
                if(state==3){
                      var oldid = $scope.userapproves[$scope.userapproves.length-1].id;
                      var newid = result.userApproves[result.userApproves.length-1].id;
                      $scope.userapproves = $scope.userapproves.concat(result.userApproves);
                      page+=1;
                }
                $scope.$broadcast('scroll.infiniteScrollComplete');
                $scope.remove = function(ua){
                      $scope.userapproves.splice($scope.userapproves.indexOf(ua),1);
                };
                      
              }).error(function(result){
                    run = false;
                    $scope.loading = false;
                    $scope.$broadcast('scroll.infiniteScrollComplete');
                    $cordovaToast.showShortBottom('数据加载错误，请重新加载.');
              });
        }
    }
             
})
.controller('ArchiveDetailCtrl', function($scope, $stateParams, $http,locals) {
    var alid = $stateParams.alId;
    var url = locals.get('url');
    var sessionid = locals.get('sessionid');
    $http.post(url+'archive/tel.archive.htm;jsessionid='+sessionid,{id:alid})
    .success(function(result){
        $scope.al = result.al;
    });
});
