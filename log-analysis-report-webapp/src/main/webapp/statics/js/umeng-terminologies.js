String.prototype.byteLength = function() {
 var cArr = this.match(/[^\x00-\xff]/ig);
 return this.length + (cArr == null ? 0 : cArr.length);
}

String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function checkWords(len, evt){
    if(evt==null) evt = window.event;
    var src = evt.srcElement ? evt.srcElement : evt.target;
    var str=src.value.trim();
    var myLen = 0;
    for(i=0;(i<str.length)&&(myLen<=len);i++){
        if(str.charCodeAt(i)>0&&str.charCodeAt(i)<128)            myLen++;
        else            myLen+=2;
    }
    if(myLen>len){
        //alert("您输入超过限定长度");
        src.value=str.substring(0,i-1);
    }
}

var Umeng = Umeng || {}

Umeng.terminologies = {
  launches                     : "一个用户对应用程序的一次使用记为一次启动",
  launched_users               : "当天启动过应用程序的用户",
  launched_users_today         : "今天启动过应用程序的用户",
  launched_users_yesterday     : "昨天启动过应用程序的用户",
  new_users                    : "新安装应用(或者从老版本升级到装有友盟SDK新版本)的用户. 用户只有在启动应用程序,并且可以联网的情况下才能被统计到.",
  new_users_today              : "今天新用户",
  new_users_yesterday          : "昨天新用户",
  new_users_percentage         : "当天新用户 / 当天启动用户",
  seconds_per_launch           : "用户平均单次使用应用程序的时间",
  active_rate                  : "当天启动用户/累计用户",
  total_users                  : "截止当前，启动过应用程序的用户总数.(多个不同的模拟器只会被算作为一个新用户，因为所有模拟器的ID(IMEI)都为00000000000000)",
  total_launches               : "截止当前，应用程序的启动总数",
  active_users_7days           : "截止昨天，7天内启动过应用程序的用户总数",
  active_users_14days          : "截止昨天，过去14天内, 启动过应用程序的用户",
  silent_users_14days          : "截止昨天, 过去14天内，没有启动过应用程序的用户",
  return_users_percentage      : "截止昨天，至少在2天里启动过应用程序的用户总数（回头用户/累计用户）",
  return_users                 : "截止昨天，至少在2天里启动过应用程序的用户总数",
  total_new_users_for_period   : "该起止时间内，新增用户总数",
  today_stat                   : "因为考虑到时差和缓存延迟发送等原因，友盟的所有统计时间以客户端传过来的时间为准，所以北美和欧洲部分国家的用户会被算作北京时间昨天的用户.",
  yesterday_stat               : "为什么昨天的用户还在增长？因为由于缓存和时差等原因，今天还能够收到属于昨天的用户，例如北京时间上午10点时，美国西部时间依然处在昨天.",
  new_users_today_on_app_version : "老版本升级至当前新版不会算做新用户",
  benchmark_new_users          : "这里取过去7天里每日新增用户的平均值",
  benchmark_launched_users     : "这里取过去7天里每日启动用户的平均值",
  benchmark_launches           : "这里取过去7天里每日启动次数的平均值",
  benchmark_senconds_per_launch : "用户平均单次使用应用程序的时间在昨天的平均数",
  average_data_for_all_apps    : '见下面注解中的"平均数据计算方法"',
  rank_in_all_apps             : "表示您的应用在该指标上排名所有应用的前 X%",
  rank_in_category             : "表示您的应用在该指标上排名同类型应用的前 X%。",
  average_data_for_category    : "在同类别应用上的平均数据",
  activity                     : "界面，即Android系统里的一个Activity",
  entries                      : "在当前应用程序版本上，用户进入当前Activity的总次数 (括号里的% 表示进入该Activity的比例)",
  duration                     : "用户每次进入当前Activity的平均停留时间",
  duration_distribution        : "用户在当前Activity消耗的时间占总使用时间的百分比",
  exit_prob                    : "用户从当前Activity离开应用程序的概率",
  next_activity                : "用户从当前Activity进入其他Activity的可能性分布情况",
  path                         : "用户对您应用各个界面的访问情况和跳转行为分析。您可以通过此报告了解用户最关心或最不关心应用中的哪些内容，从而有效地优化界面展现和内容组织形式。",
  total_gain_downloads 			: "其他应用中推荐此应用的下载次数",
  total_cross_promotion_downloads : "此应用中推荐其他应用的下载次数",
  total_house_ad_downloads           : "此应用的自主推广应用的下载次数",
  
  total_apps_users                  : "全部应用的用户数总和",
  total_apps_today_launches               : "全部应用的今日启动次数总和",
  total_apps_today_new_users         : "全部应用的今日新增用户数总和",
  total_apps_today_active_users         : "全部应用的今日活跃用户数总和"
	  
}

