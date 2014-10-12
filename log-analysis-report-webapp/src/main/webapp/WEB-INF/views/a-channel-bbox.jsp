<div class="bbox_con">
	<div class="statsTableHeader">
		<strong>渠道统计</strong> <span class="question fr"></span>
		<div class="clear"></div>
	</div>
	<div class="event_show_tips" style="display: block; ">
		<div class="fr event_tip_close">close</div>
		<div class="fl">
			<ul>
				<li class="item">想简化您的渠道报表吗？想让您的渠道合作伙伴方便的查询到推广效果数据么？
					<ul>
						<li class="subitem">建议您 <a href="#" onclick="return false;"
							style="cursor:not-allowed;" title="您当前是演示用户，请您登录后使用该功能 。">使用渠道管理功能</a>。
						</li>
					</ul></li>
				<li class="item">您可以对若干个原始渠道进行合并，合并后将只显示加和的统计结果，您也可以随时进行拆分，还原为原始渠道显示。同时，您可以删除过期的渠道，被删除的渠道数据将不再显示。</li>
				<li class="item">此外，您还可以为每个渠道开通一个渠道账户，渠道账户可以登录指定页面，查看应用在该渠道任意时间段内的新增用户、启动用户以及累计用户。</li>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<div class="time_select_wrap">
		<div class="statsTableHeader">
			<strong>选择时段</strong>
			<div class="selbox">
				<input class="datainp first_date date" type="date"
					value="2012-01-07" name="start_date"> <span>到</span> <input
					class="datainp last_date date" type="date" value="2012-02-06"
					name="end_date"> <input id="ad_man" name="ad_man"
					type="hidden"> <input id="other" name="other" type="hidden">
				<input type="button" class="upbtn" value="&nbsp;更新&nbsp;"
					onclick="subm(this)">

			</div>

			<div class="mnum" style="color:#444">
				<a
					href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel?period=days_7"
					class="">过去一周</a> | <a class="current">过去一月</a> | <a
					href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel?period=days_90"
					class="">过去三月</a> | <a
					href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel?period=days_all"
					class="">全部</a>
			</div>

			<div class="clear"></div>

		</div>

		<script type="text/javascript">
			$(function() {
				$('.period_selector').each(
						function() {
							var period_param = $(this).attr('href').replace(
									'?', '');
							var base_url = window.location.href;
							base_url.replace(/[\?&]start_date=[^&]+/, '')
									.replace(/[\?&]end_date=[^&]+/, '');
							if (base_url.indexOf('?') > 0) {
								if (base_url.indexOf('period=') > 0) {
									this.href = base_url.replace(
											/period=[^&]+/, period_param);
								} else {
									this.href = base_url + '&' + period_param;
								}
							} else {
								this.href = base_url + '?' + period_param;
							}
							$(this).removeClass('period_selector');
						});

			})

			function setDate(dates) {
				var dates_array = dates.split(";");
				var first_date = dates_array[0];
				var last_date = dates_array[1];
				$('#first_date').val(first_date);
				$('#last_date').val(last_date);
			}

			var subm = function(upbtn) {
				var par1 = $(upbtn).siblings('.first_date').first().val();
				var par2 = $(upbtn).siblings('.last_date').first().val();
				var par3 = $(upbtn).siblings('#ad_man').first().val();
				var par4 = $(upbtn).siblings('#other').first().val();
				var url = trimUrl(window.location.href) + "?start_date=" + par1
						+ "&end_date=" + par2 + "&ad_man=" + par3 + "&other="
						+ par4
				window.location.href = url;
			}
			function trimUrl(url) {
				if (url != null && url.length > 1) {
					var temp = url[url.length - 1] == '#' ? url.substring(0,
							url.length - 1) : url;
					var pos = temp.indexOf("?", 0);
					if (pos != -1) {
						temp = url.substring(0, pos)
					}
					return temp[temp.length - 1] == '/' ? temp.substring(0,
							temp.length - 1) : temp;
				} else
					return url;
			}
		</script>


	</div>
	<div class="blockboxbg" style="margin: 10px 0;">
		<div class="blockbox">
			<div id="a0" onclick="changeTab(0,2,'a','ac','bitem','selbitem')"
				class="bitem selbitem">新增用户</div>
			<div id="a1" onclick="changeTab(1,2,'a','ac','bitem','selbitem')"
				class="bitem">活跃用户</div>
		</div>
	</div>

	<div id="ac0" class="bitemcon" style="padding-bottom: 30px; ">
		<table class="datatab long new_silver_table" border="0"
			cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th scope="col" class="new_silver_first_n">渠道</th>
					<th scope="col">今日新用户</th>
					<th scope="col">昨日新用户</th>
					<th scope="col">前日新用户</th>
					<th scope="col">7天前新用户</th>
					<th scope="col">渠道累计新用户(%)</th>
					<th scope="col">明细</th>
				</tr>
				<tr class="new_silver_odd">
					<td class="new_silver_first_n">android markets</td>
					<td>113</td>
					<td>174</td>
					<td>195</td>
					<td>189</td>
					<td>5035(85.09 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e2d1f7e112cf72a2300193c&amp;start_date=2012-01-07&amp;end_date=2012-02-06"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
				<tr class="new_silver_even">
					<td class="new_silver_first_n">other</td>
					<td>8</td>
					<td>13</td>
					<td>23</td>
					<td>18</td>
					<td>540(9.13 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e378a66112cf74cd7000005&amp;start_date=2012-01-07&amp;end_date=2012-02-06"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
				<tr class="new_silver_odd">
					<td class="new_silver_first_n">homepage</td>
					<td>4</td>
					<td>13</td>
					<td>7</td>
					<td>6</td>
					<td>312(5.27 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e2d1f7e112cf72a2300193e&amp;start_date=2012-01-07&amp;end_date=2012-02-06"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
				<tr class="new_silver_even">
					<td class="new_silver_first_n">market</td>
					<td>0</td>
					<td>1</td>
					<td>0</td>
					<td>0</td>
					<td>9(0.15 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e2d1f7e112cf72a23001940&amp;start_date=2012-01-07&amp;end_date=2012-02-06"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
				<tr class="new_silver_odd">
					<td class="new_silver_first_n">bbs</td>
					<td>0</td>
					<td>0</td>
					<td>1</td>
					<td>1</td>
					<td>21(0.35 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e2d1f7e112cf72a2300193d&amp;start_date=2012-01-07&amp;end_date=2012-02-06"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="ac1" class="bitemcon"
		style="display:none;padding-bottom: 30px; ">
		<table class="datatab long new_silver_table" border="0"
			cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th scope="col" class="new_silver_first_n">渠道</th>
					<th scope="col">今日启动用户</th>
					<th scope="col">昨日启动用户</th>
					<th scope="col">前日启动用户</th>
					<th scope="col">7天前启动用户</th>
					<th scope="col">渠道累计用户(%)</th>
					<th scope="col">明细</th>
				</tr>
				<tr class="new_silver_even">
					<td class="new_silver_first_n">android markets</td>
					<td>1392</td>
					<td>2004</td>
					<td>2057</td>
					<td>2119</td>
					<td>106457(49.25 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e2d1f7e112cf72a2300193c"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
				<tr class="new_silver_odd">
					<td class="new_silver_first_n">other</td>
					<td>187</td>
					<td>251</td>
					<td>249</td>
					<td>248</td>
					<td>24609(11.39 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e378a66112cf74cd7000005"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
				<tr class="new_silver_even">
					<td class="new_silver_first_n">homepage</td>
					<td>289</td>
					<td>392</td>
					<td>405</td>
					<td>434</td>
					<td>66937(30.97 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e2d1f7e112cf72a2300193e"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
				<tr class="new_silver_odd">
					<td class="new_silver_first_n">market</td>
					<td>51</td>
					<td>78</td>
					<td>66</td>
					<td>80</td>
					<td>16135(7.46 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e2d1f7e112cf72a23001940"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
				<tr class="new_silver_even">
					<td class="new_silver_first_n">bbs</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>16</td>
					<td>2008(0.93 %)</td>
					<td><a
						href="/apps/b2700046c68c14d1744d41c4/reports/daily_stats_detail_for_channel?channel_id=4e2d1f7e112cf72a2300193d"
						class="load_channel_detail_btn">展开</a> <img alt="Loading"
						class="loading" src="/images/loading.gif?1326712342"
						style="display:none;"></td>
				</tr>
				<tr class="channel_detail_tr hidden">
					<td colspan="7" class="channel_detail_area"></td>
				</tr>
			</tbody>
		</table>
	</div>


	<table cellspacing="0" cellpadding="0" border="0"
		style="margin: 10px auto 10px; padding-top: 5px;"
		class="benchmark_tips">
		<tbody>
			<tr>
				<td>注：按<b>原始安装渠道</b>来统计新增用户、启动用户、累计用户。即用户初次安装应用的来源是渠道A，后又在渠道B更新了应用版本，但该用户仍会被记为是渠道A的启动用户、累计用户。
				</td>
			</tr>
		</tbody>
	</table>

	<div id="channel_daily_stats_filter_params" class="filter_params">
		<div class="statsTableHeader">
			<strong>渠道变化趋势</strong>
			<div class="fr" style="font-weight:normal;font-size: 13px;">
				<span class="hidden request_data_type">json</span> 过滤规则： <select
					id="stats_type" name="stats_type" style="margin-right:5px;"><option
						value="installation" selected="selected">新增用户</option>
					<option value="launch">启动用户</option>
				</select> <a href="#" class="choose_channels">选择对比渠道</a>
				<div class="hidden channel_select question_wrap_new_a_box">
					<div class="question_content_top_new_js_a">
						<h3 class="title">
							<span class="fl">选择对比渠道</span> <span class="fr tg_rss"><img
								alt=""
								src="/images/new_ui/report/report_subscribe_close_normal.png">
							</span>
							<div class="clear"></div>
						</h3>
					</div>
					<div class="question_content_center_new_b">
						<ul style="padding:15px;max-height:300px;overflow:auto;">
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a2300193c" checked=""> android
								markets</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e378a66112cf74cd7000005" checked=""> other</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a2300193d" checked=""> bbs</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a23001940" checked=""> market</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a2300193e" checked=""> homepage</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a2300193f" checked=""> huawei</li>
						</ul>
						<div class="clear"></div>
						<div style="padding:10px;">
							<span class="fl" style="margin: 5px 10px 0 0;"><input
								type="checkbox" class="select_all_btn"> 选择全部</span> <a
								href="#update_channel_trends_btn"
								class="channel_select_commit certain_btn fr">确定</a> <span
								class="fr" style="margin: 5px 10px 0 0;"><input
								type="checkbox" name="set_default" value="true">
								将所选项设置为默认显示</span>
							<div class="clear"></div>
						</div>
					</div>
					<div class="question_content_bottom_new_c"></div>
				</div>
				<input id="update_channel_trends_btn" type="button" class="upbtn"
					value="&nbsp;更新&nbsp;">
			</div>
			<div class="clear"></div>
		</div>
		<div class="time_select_wrap">
			<div class="statsTableHeader">
				<strong>选择时段</strong>
				<div class="selbox">
					<input class="datainp first_date date" type="date"
						value="2012-01-07" name="start_date"> <span>到</span> <input
						class="datainp last_date date" type="date" value="2012-02-06"
						name="end_date"> <input id="ad_man" name="ad_man"
						type="hidden"> <input id="other" name="other"
						type="hidden">

				</div>

				<div class="mnum" style="color:#444">
					<a
						href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel?period=days_7"
						class="">过去一周</a> | <a class="current">过去一月</a> | <a
						href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel?period=days_90"
						class="">过去三月</a> | <a
						href="http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel?period=days_all"
						class="">全部</a>
				</div>

				<div class="clear"></div>

			</div>

			<script type="text/javascript">
				$(function() {
					$('.period_selector').each(
							function() {
								var period_param = $(this).attr('href')
										.replace('?', '');
								var base_url = window.location.href;
								base_url.replace(/[\?&]start_date=[^&]+/, '')
										.replace(/[\?&]end_date=[^&]+/, '');
								if (base_url.indexOf('?') > 0) {
									if (base_url.indexOf('period=') > 0) {
										this.href = base_url.replace(
												/period=[^&]+/, period_param);
									} else {
										this.href = base_url + '&'
												+ period_param;
									}
								} else {
									this.href = base_url + '?' + period_param;
								}
								$(this).removeClass('period_selector');
							});

				})

				function setDate(dates) {
					var dates_array = dates.split(";");
					var first_date = dates_array[0];
					var last_date = dates_array[1];
					$('#first_date').val(first_date);
					$('#last_date').val(last_date);
				}

				var subm = function(upbtn) {
					var par1 = $(upbtn).siblings('.first_date').first().val();
					var par2 = $(upbtn).siblings('.last_date').first().val();
					var par3 = $(upbtn).siblings('#ad_man').first().val();
					var par4 = $(upbtn).siblings('#other').first().val();
					var url = trimUrl(window.location.href) + "?start_date="
							+ par1 + "&end_date=" + par2 + "&ad_man=" + par3
							+ "&other=" + par4
					window.location.href = url;
				}
				function trimUrl(url) {
					if (url != null && url.length > 1) {
						var temp = url[url.length - 1] == '#' ? url.substring(
								0, url.length - 1) : url;
						var pos = temp.indexOf("?", 0);
						if (pos != -1) {
							temp = url.substring(0, pos)
						}
						return temp[temp.length - 1] == '/' ? temp.substring(0,
								temp.length - 1) : temp;
					} else
						return url;
				}
			</script>


		</div>
	</div>
	<div id="channel_daily_stats" style="margin-bottom: 20px;">
		<div id="channel_daily_stats_canvas">
			<div class="highcharts-container" id="highcharts-0"
				style="position: relative; overflow-x: hidden; overflow-y: hidden; width: 722px; height: 400px; text-align: left; font-family: 'Lucida Grande', 'Lucida Sans Unicode', Verdana, Arial, Helvetica, sans-serif; font-size: 12px; left: 0px; top: 0px; ">
				<svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="722"
					height="400">
					<defs>
					<clipPath id="highcharts-1">
					<rect x="0" y="0" width="673" height="274" fill="none"></rect></clipPath>
					<clipPath id="highcharts-2"></clipPath>
					<clipPath id="highcharts-3">
					<rect x="0" y="0" width="662" height="274" fill="none"></rect></clipPath>
					<clipPath id="highcharts-4"></clipPath>
					<clipPath id="highcharts-5">
					<rect x="0" y="0" width="662" height="274" fill="none"></rect></clipPath>
					<clipPath id="highcharts-6"></clipPath></defs>
					<rect x="0" y="0" width="722" height="400" rx="5" ry="5"
						fill="#FFFFFF" stroke="#4572A7" stroke-width="0.000001"></rect>
					<text x="361" y="25"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#3E576F;font-size:16px;fill:#3E576F;"
						text-anchor="middle" class="highcharts-title" zIndex="1">
					<tspan x="361">新增用户变化趋势</tspan></text>
					<g class="highcharts-grid" zIndex="1"></g>
					<g class="highcharts-grid" zIndex="1">
					<path d="M 39 314.5 L 712 314.5" fill="none" stroke="#C0C0C0"
						stroke-width="1"></path>
					<path d="M 39 259.5 L 712 259.5" fill="none" stroke="#C0C0C0"
						stroke-width="1"></path>
					<path d="M 39 204.5 L 712 204.5" fill="none" stroke="#C0C0C0"
						stroke-width="1"></path>
					<path d="M 39 150.5 L 712 150.5" fill="none" stroke="#C0C0C0"
						stroke-width="1"></path>
					<path d="M 39 95.5 L 712 95.5" fill="none" stroke="#C0C0C0"
						stroke-width="1"></path>
					<path d="M 39 40.5 L 712 40.5" fill="none" stroke="#C0C0C0"
						stroke-width="1"></path></g>
					<g class="highcharts-series-group" zIndex="3">
					<g class="highcharts-series"
						clip-path="url(http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel#highcharts-1)"
						visibility="visible" transform="translate(39,40)">
					<path
						d="M 10.85483870967742 30.687999999999988 L 32.564516129032256 59.18399999999997 L 54.2741935483871 67.952 L 75.98387096774194 106.31199999999998 L 97.69354838709678 100.832 L 119.40322580645162 98.63999999999999 L 141.11290322580646 77.81599999999997 L 162.8225806451613 85.488 L 184.53225806451613 39.45599999999999 L 206.24193548387098 63.567999999999984 L 227.95161290322582 96.44799999999998 L 249.66129032258067 119.464 L 271.3709677419355 118.368 L 293.08064516129036 120.56 L 314.7903225806452 113.98399999999998 L 336.5 139.19199999999998 L 358.2096774193549 165.49599999999998 L 379.9193548387097 112.88799999999998 L 401.6290322580645 132.61599999999999 L 423.3387096774194 127.136 L 445.0483870967742 115.07999999999998 L 466.7580645161291 121.65599999999998 L 488.4677419354839 85.488 L 510.1774193548387 66.856 L 531.8870967741935 82.19999999999999 L 553.5967741935484 55.89599999999999 L 575.3064516129033 76.71999999999997 L 597.016129032258 101.928 L 618.7258064516129 60.27999999999997 L 640.4354838709678 83.29599999999999 L 662.1451612903226 150.152"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true"
						stroke-opacity="0.05" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 30.687999999999988 L 32.564516129032256 59.18399999999997 L 54.2741935483871 67.952 L 75.98387096774194 106.31199999999998 L 97.69354838709678 100.832 L 119.40322580645162 98.63999999999999 L 141.11290322580646 77.81599999999997 L 162.8225806451613 85.488 L 184.53225806451613 39.45599999999999 L 206.24193548387098 63.567999999999984 L 227.95161290322582 96.44799999999998 L 249.66129032258067 119.464 L 271.3709677419355 118.368 L 293.08064516129036 120.56 L 314.7903225806452 113.98399999999998 L 336.5 139.19199999999998 L 358.2096774193549 165.49599999999998 L 379.9193548387097 112.88799999999998 L 401.6290322580645 132.61599999999999 L 423.3387096774194 127.136 L 445.0483870967742 115.07999999999998 L 466.7580645161291 121.65599999999998 L 488.4677419354839 85.488 L 510.1774193548387 66.856 L 531.8870967741935 82.19999999999999 L 553.5967741935484 55.89599999999999 L 575.3064516129033 76.71999999999997 L 597.016129032258 101.928 L 618.7258064516129 60.27999999999997 L 640.4354838709678 83.29599999999999 L 662.1451612903226 150.152"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true"
						stroke-opacity="0.1" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 30.687999999999988 L 32.564516129032256 59.18399999999997 L 54.2741935483871 67.952 L 75.98387096774194 106.31199999999998 L 97.69354838709678 100.832 L 119.40322580645162 98.63999999999999 L 141.11290322580646 77.81599999999997 L 162.8225806451613 85.488 L 184.53225806451613 39.45599999999999 L 206.24193548387098 63.567999999999984 L 227.95161290322582 96.44799999999998 L 249.66129032258067 119.464 L 271.3709677419355 118.368 L 293.08064516129036 120.56 L 314.7903225806452 113.98399999999998 L 336.5 139.19199999999998 L 358.2096774193549 165.49599999999998 L 379.9193548387097 112.88799999999998 L 401.6290322580645 132.61599999999999 L 423.3387096774194 127.136 L 445.0483870967742 115.07999999999998 L 466.7580645161291 121.65599999999998 L 488.4677419354839 85.488 L 510.1774193548387 66.856 L 531.8870967741935 82.19999999999999 L 553.5967741935484 55.89599999999999 L 575.3064516129033 76.71999999999997 L 597.016129032258 101.928 L 618.7258064516129 60.27999999999997 L 640.4354838709678 83.29599999999999 L 662.1451612903226 150.152"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true"
						stroke-opacity="0.15000000000000002" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 30.687999999999988 L 32.564516129032256 59.18399999999997 L 54.2741935483871 67.952 L 75.98387096774194 106.31199999999998 L 97.69354838709678 100.832 L 119.40322580645162 98.63999999999999 L 141.11290322580646 77.81599999999997 L 162.8225806451613 85.488 L 184.53225806451613 39.45599999999999 L 206.24193548387098 63.567999999999984 L 227.95161290322582 96.44799999999998 L 249.66129032258067 119.464 L 271.3709677419355 118.368 L 293.08064516129036 120.56 L 314.7903225806452 113.98399999999998 L 336.5 139.19199999999998 L 358.2096774193549 165.49599999999998 L 379.9193548387097 112.88799999999998 L 401.6290322580645 132.61599999999999 L 423.3387096774194 127.136 L 445.0483870967742 115.07999999999998 L 466.7580645161291 121.65599999999998 L 488.4677419354839 85.488 L 510.1774193548387 66.856 L 531.8870967741935 82.19999999999999 L 553.5967741935484 55.89599999999999 L 575.3064516129033 76.71999999999997 L 597.016129032258 101.928 L 618.7258064516129 60.27999999999997 L 640.4354838709678 83.29599999999999 L 662.1451612903226 150.152"
						fill="none" stroke="#4572A7" stroke-width="2"></path>
					<circle cx="662.1451612903226" cy="150.152" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="640.4354838709678" cy="83.29599999999999" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="618.7258064516129" cy="60.27999999999997" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="597.016129032258" cy="101.928" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="575.3064516129033" cy="76.71999999999997" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="553.5967741935484" cy="55.89599999999999" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="531.8870967741935" cy="82.19999999999999" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="510.1774193548387" cy="66.856" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="488.4677419354839" cy="85.488" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="466.7580645161291" cy="121.65599999999998" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="445.0483870967742" cy="115.07999999999998" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="423.3387096774194" cy="127.136" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="401.6290322580645" cy="132.61599999999999" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="379.9193548387097" cy="112.88799999999998" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="358.2096774193549" cy="165.49599999999998" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="336.5" cy="139.19199999999998" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="314.7903225806452" cy="113.98399999999998" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="293.08064516129036" cy="120.56" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="271.3709677419355" cy="118.368" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="249.66129032258067" cy="119.464" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="227.95161290322582" cy="96.44799999999998" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="206.24193548387098" cy="63.567999999999984" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="184.53225806451613" cy="39.45599999999999" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="162.8225806451613" cy="85.488" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="141.11290322580646" cy="77.81599999999997" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="119.40322580645162" cy="98.63999999999999" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="97.69354838709678" cy="100.832" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="75.98387096774194" cy="106.31199999999998" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="54.2741935483871" cy="67.952" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="32.564516129032256" cy="59.18399999999997" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle>
					<circle cx="10.85483870967742" cy="30.687999999999988" r="4"
						stroke="#FFFFFF" stroke-width="0.000001" fill="#4572A7"></circle></g>
					<g class="highcharts-series"
						clip-path="url(http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel#highcharts-1)"
						visibility="visible" transform="translate(39,40)">
					<path
						d="M 10.85483870967742 259.752 L 32.564516129032256 265.23199999999997 L 54.2741935483871 255.368 L 75.98387096774194 250.984 L 97.69354838709678 260.848 L 119.40322580645162 252.07999999999998 L 141.11290322580646 253.176 L 162.8225806451613 245.504 L 184.53225806451613 254.272 L 206.24193548387098 253.176 L 227.95161290322582 252.07999999999998 L 249.66129032258067 255.368 L 271.3709677419355 249.888 L 293.08064516129036 254.272 L 314.7903225806452 258.656 L 336.5 257.56 L 358.2096774193549 260.848 L 379.9193548387097 260.848 L 401.6290322580645 258.656 L 423.3387096774194 245.504 L 445.0483870967742 256.464 L 466.7580645161291 254.272 L 488.4677419354839 246.6 L 510.1774193548387 254.272 L 531.8870967741935 249.888 L 553.5967741935484 248.792 L 575.3064516129033 258.656 L 597.016129032258 255.368 L 618.7258064516129 248.792 L 640.4354838709678 259.752 L 662.1451612903226 265.23199999999997"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true"
						stroke-opacity="0.05" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 259.752 L 32.564516129032256 265.23199999999997 L 54.2741935483871 255.368 L 75.98387096774194 250.984 L 97.69354838709678 260.848 L 119.40322580645162 252.07999999999998 L 141.11290322580646 253.176 L 162.8225806451613 245.504 L 184.53225806451613 254.272 L 206.24193548387098 253.176 L 227.95161290322582 252.07999999999998 L 249.66129032258067 255.368 L 271.3709677419355 249.888 L 293.08064516129036 254.272 L 314.7903225806452 258.656 L 336.5 257.56 L 358.2096774193549 260.848 L 379.9193548387097 260.848 L 401.6290322580645 258.656 L 423.3387096774194 245.504 L 445.0483870967742 256.464 L 466.7580645161291 254.272 L 488.4677419354839 246.6 L 510.1774193548387 254.272 L 531.8870967741935 249.888 L 553.5967741935484 248.792 L 575.3064516129033 258.656 L 597.016129032258 255.368 L 618.7258064516129 248.792 L 640.4354838709678 259.752 L 662.1451612903226 265.23199999999997"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true"
						stroke-opacity="0.1" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 259.752 L 32.564516129032256 265.23199999999997 L 54.2741935483871 255.368 L 75.98387096774194 250.984 L 97.69354838709678 260.848 L 119.40322580645162 252.07999999999998 L 141.11290322580646 253.176 L 162.8225806451613 245.504 L 184.53225806451613 254.272 L 206.24193548387098 253.176 L 227.95161290322582 252.07999999999998 L 249.66129032258067 255.368 L 271.3709677419355 249.888 L 293.08064516129036 254.272 L 314.7903225806452 258.656 L 336.5 257.56 L 358.2096774193549 260.848 L 379.9193548387097 260.848 L 401.6290322580645 258.656 L 423.3387096774194 245.504 L 445.0483870967742 256.464 L 466.7580645161291 254.272 L 488.4677419354839 246.6 L 510.1774193548387 254.272 L 531.8870967741935 249.888 L 553.5967741935484 248.792 L 575.3064516129033 258.656 L 597.016129032258 255.368 L 618.7258064516129 248.792 L 640.4354838709678 259.752 L 662.1451612903226 265.23199999999997"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true"
						stroke-opacity="0.15000000000000002" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 259.752 L 32.564516129032256 265.23199999999997 L 54.2741935483871 255.368 L 75.98387096774194 250.984 L 97.69354838709678 260.848 L 119.40322580645162 252.07999999999998 L 141.11290322580646 253.176 L 162.8225806451613 245.504 L 184.53225806451613 254.272 L 206.24193548387098 253.176 L 227.95161290322582 252.07999999999998 L 249.66129032258067 255.368 L 271.3709677419355 249.888 L 293.08064516129036 254.272 L 314.7903225806452 258.656 L 336.5 257.56 L 358.2096774193549 260.848 L 379.9193548387097 260.848 L 401.6290322580645 258.656 L 423.3387096774194 245.504 L 445.0483870967742 256.464 L 466.7580645161291 254.272 L 488.4677419354839 246.6 L 510.1774193548387 254.272 L 531.8870967741935 249.888 L 553.5967741935484 248.792 L 575.3064516129033 258.656 L 597.016129032258 255.368 L 618.7258064516129 248.792 L 640.4354838709678 259.752 L 662.1451612903226 265.23199999999997"
						fill="none" stroke="#AA4643" stroke-width="2"></path>
					<path
						d="M 662.1451612903226 261.23199999999997 L 666.1451612903226 265.23199999999997 662.1451612903226 269.23199999999997 658.1451612903226 265.23199999999997 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 640.4354838709678 255.752 L 644.4354838709678 259.752 640.4354838709678 263.752 636.4354838709678 259.752 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 618.7258064516129 244.792 L 622.7258064516129 248.792 618.7258064516129 252.792 614.7258064516129 248.792 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 597.016129032258 251.368 L 601.016129032258 255.368 597.016129032258 259.368 593.016129032258 255.368 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 575.3064516129033 254.656 L 579.3064516129033 258.656 575.3064516129033 262.656 571.3064516129033 258.656 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 553.5967741935484 244.792 L 557.5967741935484 248.792 553.5967741935484 252.792 549.5967741935484 248.792 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 531.8870967741935 245.888 L 535.8870967741935 249.888 531.8870967741935 253.888 527.8870967741935 249.888 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 510.1774193548387 250.272 L 514.1774193548388 254.272 510.1774193548387 258.272 506.1774193548387 254.272 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 488.4677419354839 242.6 L 492.4677419354839 246.6 488.4677419354839 250.6 484.4677419354839 246.6 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 466.7580645161291 250.272 L 470.7580645161291 254.272 466.7580645161291 258.272 462.7580645161291 254.272 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 445.0483870967742 252.464 L 449.0483870967742 256.464 445.0483870967742 260.464 441.0483870967742 256.464 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 423.3387096774194 241.504 L 427.3387096774194 245.504 423.3387096774194 249.504 419.3387096774194 245.504 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 401.6290322580645 254.656 L 405.6290322580645 258.656 401.6290322580645 262.656 397.6290322580645 258.656 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 379.9193548387097 256.848 L 383.9193548387097 260.848 379.9193548387097 264.848 375.9193548387097 260.848 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 358.2096774193549 256.848 L 362.2096774193549 260.848 358.2096774193549 264.848 354.2096774193549 260.848 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path d="M 336.5 253.56 L 340.5 257.56 336.5 261.56 332.5 257.56 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 314.7903225806452 254.656 L 318.7903225806452 258.656 314.7903225806452 262.656 310.7903225806452 258.656 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 293.08064516129036 250.272 L 297.08064516129036 254.272 293.08064516129036 258.272 289.08064516129036 254.272 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 271.3709677419355 245.888 L 275.3709677419355 249.888 271.3709677419355 253.888 267.3709677419355 249.888 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 249.66129032258067 251.368 L 253.66129032258067 255.368 249.66129032258067 259.368 245.66129032258067 255.368 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 227.95161290322582 248.07999999999998 L 231.95161290322582 252.07999999999998 227.95161290322582 256.08 223.95161290322582 252.07999999999998 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 206.24193548387098 249.176 L 210.24193548387098 253.176 206.24193548387098 257.176 202.24193548387098 253.176 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 184.53225806451613 250.272 L 188.53225806451613 254.272 184.53225806451613 258.272 180.53225806451613 254.272 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 162.8225806451613 241.504 L 166.8225806451613 245.504 162.8225806451613 249.504 158.8225806451613 245.504 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 141.11290322580646 249.176 L 145.11290322580646 253.176 141.11290322580646 257.176 137.11290322580646 253.176 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 119.40322580645162 248.07999999999998 L 123.40322580645162 252.07999999999998 119.40322580645162 256.08 115.40322580645162 252.07999999999998 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 97.69354838709678 256.848 L 101.69354838709678 260.848 97.69354838709678 264.848 93.69354838709678 260.848 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 75.98387096774194 246.984 L 79.98387096774194 250.984 75.98387096774194 254.984 71.98387096774194 250.984 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 54.2741935483871 251.368 L 58.2741935483871 255.368 54.2741935483871 259.368 50.2741935483871 255.368 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 32.564516129032256 261.23199999999997 L 36.564516129032256 265.23199999999997 32.564516129032256 269.23199999999997 28.564516129032256 265.23199999999997 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 10.85483870967742 255.752 L 14.85483870967742 259.752 10.85483870967742 263.752 6.85483870967742 259.752 Z"
						fill="#AA4643" stroke="#FFFFFF" stroke-width="0.000001"></path></g>
					<g class="highcharts-series"
						clip-path="url(http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel#highcharts-3)"
						visibility="visible" transform="translate(39,40)">
					<path
						d="M 10.85483870967742 267.424 L 32.564516129032256 257.56 L 54.2741935483871 268.52 L 75.98387096774194 264.136 L 97.69354838709678 266.328 L 119.40322580645162 267.424 L 141.11290322580646 263.04 L 162.8225806451613 261.944 L 184.53225806451613 259.752 L 206.24193548387098 256.464 L 227.95161290322582 258.656 L 249.66129032258067 255.368 L 271.3709677419355 255.368 L 293.08064516129036 263.04 L 314.7903225806452 260.848 L 336.5 261.944 L 358.2096774193549 267.424 L 379.9193548387097 268.52 L 401.6290322580645 266.328 L 423.3387096774194 269.616 L 445.0483870967742 264.136 L 466.7580645161291 260.848 L 488.4677419354839 264.136 L 510.1774193548387 267.424 L 531.8870967741935 261.944 L 553.5967741935484 258.656 L 575.3064516129033 255.368 L 597.016129032258 264.136 L 618.7258064516129 266.328 L 640.4354838709678 259.752 L 662.1451612903226 269.616"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true"
						stroke-opacity="0.05" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 267.424 L 32.564516129032256 257.56 L 54.2741935483871 268.52 L 75.98387096774194 264.136 L 97.69354838709678 266.328 L 119.40322580645162 267.424 L 141.11290322580646 263.04 L 162.8225806451613 261.944 L 184.53225806451613 259.752 L 206.24193548387098 256.464 L 227.95161290322582 258.656 L 249.66129032258067 255.368 L 271.3709677419355 255.368 L 293.08064516129036 263.04 L 314.7903225806452 260.848 L 336.5 261.944 L 358.2096774193549 267.424 L 379.9193548387097 268.52 L 401.6290322580645 266.328 L 423.3387096774194 269.616 L 445.0483870967742 264.136 L 466.7580645161291 260.848 L 488.4677419354839 264.136 L 510.1774193548387 267.424 L 531.8870967741935 261.944 L 553.5967741935484 258.656 L 575.3064516129033 255.368 L 597.016129032258 264.136 L 618.7258064516129 266.328 L 640.4354838709678 259.752 L 662.1451612903226 269.616"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true"
						stroke-opacity="0.1" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 267.424 L 32.564516129032256 257.56 L 54.2741935483871 268.52 L 75.98387096774194 264.136 L 97.69354838709678 266.328 L 119.40322580645162 267.424 L 141.11290322580646 263.04 L 162.8225806451613 261.944 L 184.53225806451613 259.752 L 206.24193548387098 256.464 L 227.95161290322582 258.656 L 249.66129032258067 255.368 L 271.3709677419355 255.368 L 293.08064516129036 263.04 L 314.7903225806452 260.848 L 336.5 261.944 L 358.2096774193549 267.424 L 379.9193548387097 268.52 L 401.6290322580645 266.328 L 423.3387096774194 269.616 L 445.0483870967742 264.136 L 466.7580645161291 260.848 L 488.4677419354839 264.136 L 510.1774193548387 267.424 L 531.8870967741935 261.944 L 553.5967741935484 258.656 L 575.3064516129033 255.368 L 597.016129032258 264.136 L 618.7258064516129 266.328 L 640.4354838709678 259.752 L 662.1451612903226 269.616"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true"
						stroke-opacity="0.15000000000000002" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 267.424 L 32.564516129032256 257.56 L 54.2741935483871 268.52 L 75.98387096774194 264.136 L 97.69354838709678 266.328 L 119.40322580645162 267.424 L 141.11290322580646 263.04 L 162.8225806451613 261.944 L 184.53225806451613 259.752 L 206.24193548387098 256.464 L 227.95161290322582 258.656 L 249.66129032258067 255.368 L 271.3709677419355 255.368 L 293.08064516129036 263.04 L 314.7903225806452 260.848 L 336.5 261.944 L 358.2096774193549 267.424 L 379.9193548387097 268.52 L 401.6290322580645 266.328 L 423.3387096774194 269.616 L 445.0483870967742 264.136 L 466.7580645161291 260.848 L 488.4677419354839 264.136 L 510.1774193548387 267.424 L 531.8870967741935 261.944 L 553.5967741935484 258.656 L 575.3064516129033 255.368 L 597.016129032258 264.136 L 618.7258064516129 266.328 L 640.4354838709678 259.752 L 662.1451612903226 269.616"
						fill="none" stroke="#89A54E" stroke-width="2"></path>
					<path
						d="M 659.3171612903226 266.788 L 664.9731612903225 266.788 664.9731612903225 272.44399999999996 659.3171612903226 272.44399999999996 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 637.6074838709678 256.92400000000004 L 643.2634838709678 256.92400000000004 643.2634838709678 262.58 637.6074838709678 262.58 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 615.897806451613 263.5 L 621.5538064516129 263.5 621.5538064516129 269.15599999999995 615.897806451613 269.15599999999995 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 594.1881290322581 261.30800000000005 L 599.844129032258 261.30800000000005 599.844129032258 266.964 594.1881290322581 266.964 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 572.4784516129033 252.54 L 578.1344516129033 252.54 578.1344516129033 258.19599999999997 572.4784516129033 258.19599999999997 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 550.7687741935484 255.828 L 556.4247741935484 255.828 556.4247741935484 261.484 550.7687741935484 261.484 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 529.0590967741936 259.11600000000004 L 534.7150967741935 259.11600000000004 534.7150967741935 264.772 529.0590967741936 264.772 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 507.34941935483874 264.596 L 513.0054193548388 264.596 513.0054193548388 270.25199999999995 507.34941935483874 270.25199999999995 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 485.6397419354839 261.30800000000005 L 491.2957419354839 261.30800000000005 491.2957419354839 266.964 485.6397419354839 266.964 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 463.9300645161291 258.02000000000004 L 469.58606451612906 258.02000000000004 469.58606451612906 263.676 463.9300645161291 263.676 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 442.22038709677423 261.30800000000005 L 447.8763870967742 261.30800000000005 447.8763870967742 266.964 442.22038709677423 266.964 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 420.5107096774194 266.788 L 426.16670967741936 266.788 426.16670967741936 272.44399999999996 420.5107096774194 272.44399999999996 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 398.80103225806454 263.5 L 404.4570322580645 263.5 404.4570322580645 269.15599999999995 398.80103225806454 269.15599999999995 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 377.0913548387097 265.692 L 382.74735483870967 265.692 382.74735483870967 271.34799999999996 377.0913548387097 271.34799999999996 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 355.3816774193549 264.596 L 361.03767741935485 264.596 361.03767741935485 270.25199999999995 355.3816774193549 270.25199999999995 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 333.672 259.11600000000004 L 339.328 259.11600000000004 339.328 264.772 333.672 264.772 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 311.9623225806452 258.02000000000004 L 317.61832258064516 258.02000000000004 317.61832258064516 263.676 311.9623225806452 263.676 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 290.2526451612904 260.21200000000005 L 295.90864516129034 260.21200000000005 295.90864516129034 265.868 290.2526451612904 265.868 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 268.5429677419355 252.54 L 274.19896774193546 252.54 274.19896774193546 258.19599999999997 268.5429677419355 258.19599999999997 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 246.83329032258067 252.54 L 252.48929032258067 252.54 252.48929032258067 258.19599999999997 246.83329032258067 258.19599999999997 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 225.12361290322582 255.828 L 230.77961290322582 255.828 230.77961290322582 261.484 225.12361290322582 261.484 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 203.41393548387097 253.636 L 209.06993548387098 253.636 209.06993548387098 259.292 203.41393548387097 259.292 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 181.70425806451613 256.92400000000004 L 187.36025806451613 256.92400000000004 187.36025806451613 262.58 181.70425806451613 262.58 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 159.9945806451613 259.11600000000004 L 165.6505806451613 259.11600000000004 165.6505806451613 264.772 159.9945806451613 264.772 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 138.28490322580646 260.21200000000005 L 143.94090322580647 260.21200000000005 143.94090322580647 265.868 138.28490322580646 265.868 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 116.57522580645161 264.596 L 122.23122580645162 264.596 122.23122580645162 270.25199999999995 116.57522580645161 270.25199999999995 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 94.86554838709678 263.5 L 100.52154838709679 263.5 100.52154838709679 269.15599999999995 94.86554838709678 269.15599999999995 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 73.15587096774193 261.30800000000005 L 78.81187096774194 261.30800000000005 78.81187096774194 266.964 73.15587096774193 266.964 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 51.4461935483871 265.692 L 57.102193548387106 265.692 57.102193548387106 271.34799999999996 51.4461935483871 271.34799999999996 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 29.736516129032257 254.732 L 35.39251612903226 254.732 35.39251612903226 260.388 29.736516129032257 260.388 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 8.02683870967742 264.596 L 13.68283870967742 264.596 13.68283870967742 270.25199999999995 8.02683870967742 270.25199999999995 Z"
						fill="#89A54E" stroke="#FFFFFF" stroke-width="0.000001"></path></g>
					<g class="highcharts-series"
						clip-path="url(http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel#highcharts-1)"
						visibility="visible" transform="translate(39,40)">
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 272.904 L 75.98387096774194 274 L 97.69354838709678 272.904 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 272.904 L 184.53225806451613 270.712 L 206.24193548387098 272.904 L 227.95161290322582 272.904 L 249.66129032258067 274 L 271.3709677419355 272.904 L 293.08064516129036 274 L 314.7903225806452 271.808 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 271.808 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 272.904 L 510.1774193548387 272.904 L 531.8870967741935 271.808 L 553.5967741935484 272.904 L 575.3064516129033 272.904 L 597.016129032258 274 L 618.7258064516129 272.904 L 640.4354838709678 274 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true"
						stroke-opacity="0.05" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 272.904 L 75.98387096774194 274 L 97.69354838709678 272.904 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 272.904 L 184.53225806451613 270.712 L 206.24193548387098 272.904 L 227.95161290322582 272.904 L 249.66129032258067 274 L 271.3709677419355 272.904 L 293.08064516129036 274 L 314.7903225806452 271.808 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 271.808 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 272.904 L 510.1774193548387 272.904 L 531.8870967741935 271.808 L 553.5967741935484 272.904 L 575.3064516129033 272.904 L 597.016129032258 274 L 618.7258064516129 272.904 L 640.4354838709678 274 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true"
						stroke-opacity="0.1" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 272.904 L 75.98387096774194 274 L 97.69354838709678 272.904 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 272.904 L 184.53225806451613 270.712 L 206.24193548387098 272.904 L 227.95161290322582 272.904 L 249.66129032258067 274 L 271.3709677419355 272.904 L 293.08064516129036 274 L 314.7903225806452 271.808 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 271.808 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 272.904 L 510.1774193548387 272.904 L 531.8870967741935 271.808 L 553.5967741935484 272.904 L 575.3064516129033 272.904 L 597.016129032258 274 L 618.7258064516129 272.904 L 640.4354838709678 274 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true"
						stroke-opacity="0.15000000000000002" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 272.904 L 75.98387096774194 274 L 97.69354838709678 272.904 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 272.904 L 184.53225806451613 270.712 L 206.24193548387098 272.904 L 227.95161290322582 272.904 L 249.66129032258067 274 L 271.3709677419355 272.904 L 293.08064516129036 274 L 314.7903225806452 271.808 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 271.808 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 272.904 L 510.1774193548387 272.904 L 531.8870967741935 271.808 L 553.5967741935484 272.904 L 575.3064516129033 272.904 L 597.016129032258 274 L 618.7258064516129 272.904 L 640.4354838709678 274 L 662.1451612903226 274"
						fill="none" stroke="#80699B" stroke-width="2"></path>
					<path
						d="M 662.1451612903226 268.68 L 666.1451612903226 276.68 658.1451612903226 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 640.4354838709678 268.68 L 644.4354838709678 276.68 636.4354838709678 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 618.7258064516129 267.584 L 622.7258064516129 275.584 614.7258064516129 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 597.016129032258 268.68 L 601.016129032258 276.68 593.016129032258 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 575.3064516129033 267.584 L 579.3064516129033 275.584 571.3064516129033 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 553.5967741935484 267.584 L 557.5967741935484 275.584 549.5967741935484 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 531.8870967741935 266.488 L 535.8870967741935 274.488 527.8870967741935 274.488 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 510.1774193548387 267.584 L 514.1774193548388 275.584 506.1774193548387 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 488.4677419354839 267.584 L 492.4677419354839 275.584 484.4677419354839 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 466.7580645161291 268.68 L 470.7580645161291 276.68 462.7580645161291 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 445.0483870967742 268.68 L 449.0483870967742 276.68 441.0483870967742 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 423.3387096774194 268.68 L 427.3387096774194 276.68 419.3387096774194 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 401.6290322580645 266.488 L 405.6290322580645 274.488 397.6290322580645 274.488 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 379.9193548387097 268.68 L 383.9193548387097 276.68 375.9193548387097 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 358.2096774193549 268.68 L 362.2096774193549 276.68 354.2096774193549 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path d="M 336.5 268.68 L 340.5 276.68 332.5 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 314.7903225806452 266.488 L 318.7903225806452 274.488 310.7903225806452 274.488 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 293.08064516129036 268.68 L 297.08064516129036 276.68 289.08064516129036 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 271.3709677419355 267.584 L 275.3709677419355 275.584 267.3709677419355 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 249.66129032258067 268.68 L 253.66129032258067 276.68 245.66129032258067 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 227.95161290322582 267.584 L 231.95161290322582 275.584 223.95161290322582 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 206.24193548387098 267.584 L 210.24193548387098 275.584 202.24193548387098 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 184.53225806451613 265.392 L 188.53225806451613 273.392 180.53225806451613 273.392 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 162.8225806451613 267.584 L 166.8225806451613 275.584 158.8225806451613 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 141.11290322580646 268.68 L 145.11290322580646 276.68 137.11290322580646 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 119.40322580645162 268.68 L 123.40322580645162 276.68 115.40322580645162 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 97.69354838709678 267.584 L 101.69354838709678 275.584 93.69354838709678 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 75.98387096774194 268.68 L 79.98387096774194 276.68 71.98387096774194 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 54.2741935483871 267.584 L 58.2741935483871 275.584 50.2741935483871 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 32.564516129032256 267.584 L 36.564516129032256 275.584 28.564516129032256 275.584 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 10.85483870967742 268.68 L 14.85483870967742 276.68 6.85483870967742 276.68 Z"
						fill="#80699B" stroke="#FFFFFF" stroke-width="0.000001"></path></g>
					<g class="highcharts-series"
						clip-path="url(http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel#highcharts-5)"
						visibility="visible" transform="translate(39,40)">
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 272.904 L 162.8225806451613 272.904 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 272.904 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 272.904 L 553.5967741935484 271.808 L 575.3064516129033 274 L 597.016129032258 272.904 L 618.7258064516129 274 L 640.4354838709678 272.904 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true"
						stroke-opacity="0.05" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 272.904 L 162.8225806451613 272.904 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 272.904 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 272.904 L 553.5967741935484 271.808 L 575.3064516129033 274 L 597.016129032258 272.904 L 618.7258064516129 274 L 640.4354838709678 272.904 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true"
						stroke-opacity="0.1" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 272.904 L 162.8225806451613 272.904 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 272.904 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 272.904 L 553.5967741935484 271.808 L 575.3064516129033 274 L 597.016129032258 272.904 L 618.7258064516129 274 L 640.4354838709678 272.904 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true"
						stroke-opacity="0.15000000000000002" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 272.904 L 162.8225806451613 272.904 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 272.904 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 272.904 L 553.5967741935484 271.808 L 575.3064516129033 274 L 597.016129032258 272.904 L 618.7258064516129 274 L 640.4354838709678 272.904 L 662.1451612903226 274"
						fill="none" stroke="#3D96AE" stroke-width="2"></path>
					<path
						d="M 662.1451612903226 279.32 L 658.1451612903226 271.32 666.1451612903226 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 640.4354838709678 278.224 L 636.4354838709678 270.224 644.4354838709678 270.224 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 618.7258064516129 279.32 L 614.7258064516129 271.32 622.7258064516129 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 597.016129032258 278.224 L 593.016129032258 270.224 601.016129032258 270.224 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 575.3064516129033 279.32 L 571.3064516129033 271.32 579.3064516129033 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 553.5967741935484 277.128 L 549.5967741935484 269.128 557.5967741935484 269.128 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 531.8870967741935 278.224 L 527.8870967741935 270.224 535.8870967741935 270.224 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 510.1774193548387 279.32 L 506.1774193548387 271.32 514.1774193548388 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 488.4677419354839 279.32 L 484.4677419354839 271.32 492.4677419354839 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 466.7580645161291 279.32 L 462.7580645161291 271.32 470.7580645161291 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 445.0483870967742 279.32 L 441.0483870967742 271.32 449.0483870967742 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 423.3387096774194 279.32 L 419.3387096774194 271.32 427.3387096774194 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 401.6290322580645 278.224 L 397.6290322580645 270.224 405.6290322580645 270.224 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 379.9193548387097 279.32 L 375.9193548387097 271.32 383.9193548387097 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 358.2096774193549 279.32 L 354.2096774193549 271.32 362.2096774193549 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path d="M 336.5 279.32 L 332.5 271.32 340.5 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 314.7903225806452 279.32 L 310.7903225806452 271.32 318.7903225806452 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 293.08064516129036 279.32 L 289.08064516129036 271.32 297.08064516129036 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 271.3709677419355 279.32 L 267.3709677419355 271.32 275.3709677419355 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 249.66129032258067 279.32 L 245.66129032258067 271.32 253.66129032258067 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 227.95161290322582 279.32 L 223.95161290322582 271.32 231.95161290322582 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 206.24193548387098 279.32 L 202.24193548387098 271.32 210.24193548387098 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 184.53225806451613 279.32 L 180.53225806451613 271.32 188.53225806451613 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 162.8225806451613 278.224 L 158.8225806451613 270.224 166.8225806451613 270.224 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 141.11290322580646 278.224 L 137.11290322580646 270.224 145.11290322580646 270.224 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 119.40322580645162 279.32 L 115.40322580645162 271.32 123.40322580645162 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 97.69354838709678 279.32 L 93.69354838709678 271.32 101.69354838709678 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 75.98387096774194 279.32 L 71.98387096774194 271.32 79.98387096774194 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 54.2741935483871 279.32 L 50.2741935483871 271.32 58.2741935483871 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 32.564516129032256 278.224 L 28.564516129032256 270.224 36.564516129032256 270.224 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path>
					<path
						d="M 10.85483870967742 279.32 L 6.85483870967742 271.32 14.85483870967742 271.32 Z"
						fill="#3D96AE" stroke="#FFFFFF" stroke-width="0.000001"></path></g>
					<g class="highcharts-series"
						clip-path="url(http://www.umeng.com/apps/b2700046c68c14d1744d41c4/reports/channel#highcharts-1)"
						visibility="visible" transform="translate(39,40)">
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 274 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 274 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 274 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 274 L 553.5967741935484 274 L 575.3064516129033 274 L 597.016129032258 274 L 618.7258064516129 274 L 640.4354838709678 274 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true"
						stroke-opacity="0.05" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 274 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 274 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 274 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 274 L 553.5967741935484 274 L 575.3064516129033 274 L 597.016129032258 274 L 618.7258064516129 274 L 640.4354838709678 274 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true"
						stroke-opacity="0.1" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 274 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 274 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 274 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 274 L 553.5967741935484 274 L 575.3064516129033 274 L 597.016129032258 274 L 618.7258064516129 274 L 640.4354838709678 274 L 662.1451612903226 274"
						fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true"
						stroke-opacity="0.15000000000000002" transform="translate(1,1)"></path>
					<path
						d="M 10.85483870967742 274 L 32.564516129032256 274 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 274 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 274 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 274 L 553.5967741935484 274 L 575.3064516129033 274 L 597.016129032258 274 L 618.7258064516129 274 L 640.4354838709678 274 L 662.1451612903226 274"
						fill="none" stroke="#DB843D" stroke-width="2"></path>
					<circle cx="662.1451612903226" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="640.4354838709678" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="618.7258064516129" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="597.016129032258" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="575.3064516129033" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="553.5967741935484" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="531.8870967741935" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="510.1774193548387" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="488.4677419354839" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="466.7580645161291" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="445.0483870967742" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="423.3387096774194" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="401.6290322580645" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="379.9193548387097" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="358.2096774193549" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="336.5" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="314.7903225806452" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="293.08064516129036" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="271.3709677419355" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="249.66129032258067" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="227.95161290322582" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="206.24193548387098" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="184.53225806451613" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="162.8225806451613" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="141.11290322580646" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="119.40322580645162" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="97.69354838709678" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="75.98387096774194" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="54.2741935483871" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="32.564516129032256" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle>
					<circle cx="10.85483870967742" cy="274" r="4" stroke="#FFFFFF"
						stroke-width="0.000001" fill="#DB843D"></circle></g></g>
					<g class="highcharts-legend" zIndex="7"
						transform="translate(88,359)">
					<rect x="0.5" y="0.5" width="546" height="26" rx="5" ry="5"
						fill="none" stroke="#909090" stroke-width="1" visibility="visible"></rect>
					<text x="30" y="18"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#3E576F;fill:#3E576F;"
						zIndex="2">
					<tspan x="30">android markets</tspan></text>
					<path d="M -21 0 L -5 0" fill="none" stroke-width="2" zIndex="2"
						stroke="#4572A7" transform="translate(30,14)"></path>
					<text x="167" y="18"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#3E576F;fill:#3E576F;"
						zIndex="2">
					<tspan x="167">other</tspan></text>
					<path d="M -21 0 L -5 0" fill="none" stroke-width="2" zIndex="2"
						stroke="#AA4643" transform="translate(167,14)"></path>
					<text x="238" y="18"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#3E576F;fill:#3E576F;"
						zIndex="2">
					<tspan x="238">homepage</tspan></text>
					<path d="M -21 0 L -5 0" fill="none" stroke-width="2" zIndex="2"
						stroke="#89A54E" transform="translate(238,14)"></path>
					<text x="340" y="18"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#3E576F;fill:#3E576F;"
						zIndex="2">
					<tspan x="340">bbs</tspan></text>
					<path d="M -21 0 L -5 0" fill="none" stroke-width="2" zIndex="2"
						stroke="#80699B" transform="translate(340,14)"></path>
					<text x="403" y="18"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#3E576F;fill:#3E576F;"
						zIndex="2">
					<tspan x="403">market</tspan></text>
					<path d="M -21 0 L -5 0" fill="none" stroke-width="2" zIndex="2"
						stroke="#3D96AE" transform="translate(403,14)"></path>
					<text x="485" y="18"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#3E576F;fill:#3E576F;"
						zIndex="2">
					<tspan x="485">huawei</tspan></text>
					<path d="M -21 0 L -5 0" fill="none" stroke-width="2" zIndex="2"
						stroke="#DB843D" transform="translate(485,14)"></path>
					<circle cx="17" cy="14" r="4" zIndex="3" stroke="#4572A7"
						fill="#4572A7"></circle>
					<path d="M 154 10 L 158 14 154 18 150 14 Z" fill="#AA4643"
						zIndex="3" stroke="#AA4643"></path>
					<path
						d="M 222.172 11.172 L 227.828 11.172 227.828 16.828 222.172 16.828 Z"
						fill="#89A54E" zIndex="3" stroke="#89A54E"></path>
					<path d="M 327 8.68 L 331 16.68 323 16.68 Z" fill="#80699B"
						zIndex="3" stroke="#80699B"></path>
					<path d="M 390 19.32 L 386 11.32 394 11.32 Z" fill="#3D96AE"
						zIndex="3" stroke="#3D96AE"></path>
					<circle cx="472" cy="14" r="4" zIndex="3" stroke="#DB843D"
						fill="#DB843D"></circle></g>
					<g class="highcharts-axis" zIndex="7">
					<text x="49.85483870967742" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 49.85483870967742 328)"
						visibility="visible">
					<tspan x="49.85483870967742">01-07</tspan></text>
					<text x="71.56451612903226" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 71.56451612903226 328)"
						visibility="hidden">
					<tspan x="71.56451612903226">01-08</tspan></text>
					<text x="93.27419354838709" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 93.27419354838709 328)"
						visibility="hidden">
					<tspan x="93.27419354838709">01-09</tspan></text>
					<text x="114.98387096774194" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 114.98387096774194 328)"
						visibility="visible">
					<tspan x="114.98387096774194">01-10</tspan></text>
					<text x="136.6935483870968" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 136.6935483870968 328)"
						visibility="hidden">
					<tspan x="136.6935483870968">01-11</tspan></text>
					<text x="158.40322580645162" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 158.40322580645162 328)"
						visibility="hidden">
					<tspan x="158.40322580645162">01-12</tspan></text>
					<text x="180.11290322580646" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 180.11290322580646 328)"
						visibility="visible">
					<tspan x="180.11290322580646">01-13</tspan></text>
					<text x="201.8225806451613" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 201.8225806451613 328)"
						visibility="hidden">
					<tspan x="201.8225806451613">01-14</tspan></text>
					<text x="223.53225806451616" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 223.53225806451616 328)"
						visibility="hidden">
					<tspan x="223.53225806451616">01-15</tspan></text>
					<text x="245.241935483871" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 245.241935483871 328)"
						visibility="visible">
					<tspan x="245.241935483871">01-16</tspan></text>
					<text x="266.9516129032258" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 266.9516129032258 328)"
						visibility="hidden">
					<tspan x="266.9516129032258">01-17</tspan></text>
					<text x="288.6612903225806" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 288.6612903225806 328)"
						visibility="hidden">
					<tspan x="288.6612903225806">01-18</tspan></text>
					<text x="310.3709677419355" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 310.3709677419355 328)"
						visibility="visible">
					<tspan x="310.3709677419355">01-19</tspan></text>
					<text x="332.0806451612903" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 332.0806451612903 328)"
						visibility="hidden">
					<tspan x="332.0806451612903">01-20</tspan></text>
					<text x="353.7903225806452" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 353.7903225806452 328)"
						visibility="hidden">
					<tspan x="353.7903225806452">01-21</tspan></text>
					<text x="375.5" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 375.5 328)"
						visibility="visible">
					<tspan x="375.5">01-22</tspan></text>
					<text x="397.2096774193548" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 397.2096774193548 328)"
						visibility="hidden">
					<tspan x="397.2096774193548">01-23</tspan></text>
					<text x="418.9193548387097" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 418.9193548387097 328)"
						visibility="hidden">
					<tspan x="418.9193548387097">01-24</tspan></text>
					<text x="440.6290322580645" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 440.6290322580645 328)"
						visibility="visible">
					<tspan x="440.6290322580645">01-25</tspan></text>
					<text x="462.3387096774194" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 462.3387096774194 328)"
						visibility="hidden">
					<tspan x="462.3387096774194">01-26</tspan></text>
					<text x="484.0483870967742" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 484.0483870967742 328)"
						visibility="hidden">
					<tspan x="484.0483870967742">01-27</tspan></text>
					<text x="505.758064516129" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 505.758064516129 328)"
						visibility="visible">
					<tspan x="505.758064516129">01-28</tspan></text>
					<text x="527.4677419354839" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 527.4677419354839 328)"
						visibility="hidden">
					<tspan x="527.4677419354839">01-29</tspan></text>
					<text x="549.1774193548387" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 549.1774193548387 328)"
						visibility="hidden">
					<tspan x="549.1774193548387">01-30</tspan></text>
					<text x="570.8870967741935" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 570.8870967741935 328)"
						visibility="visible">
					<tspan x="570.8870967741935">01-31</tspan></text>
					<text x="592.5967741935484" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 592.5967741935484 328)"
						visibility="hidden">
					<tspan x="592.5967741935484">02-01</tspan></text>
					<text x="614.3064516129033" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 614.3064516129033 328)"
						visibility="hidden">
					<tspan x="614.3064516129033">02-02</tspan></text>
					<text x="636.016129032258" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 636.016129032258 328)"
						visibility="visible">
					<tspan x="636.016129032258">02-03</tspan></text>
					<text x="657.7258064516129" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 657.7258064516129 328)"
						visibility="hidden">
					<tspan x="657.7258064516129">02-04</tspan></text>
					<text x="679.4354838709678" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 679.4354838709678 328)"
						visibility="hidden">
					<tspan x="679.4354838709678">02-05</tspan></text>
					<text x="701.1451612903226" y="328"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end" transform="rotate(-90 701.1451612903226 328)"
						visibility="visible">
					<tspan x="701.1451612903226">02-06</tspan></text>
					<path d="M 61.5 314 L 61.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 82.5 314 L 82.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 104.5 314 L 104.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 126.5 314 L 126.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 148.5 314 L 148.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 169.5 314 L 169.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 191.5 314 L 191.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 213.5 314 L 213.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 234.5 314 L 234.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 256.5 314 L 256.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 278.5 314 L 278.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 300.5 314 L 300.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 321.5 314 L 321.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 343.5 314 L 343.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 365.5 314 L 365.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 386.5 314 L 386.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 408.5 314 L 408.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 430.5 314 L 430.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 451.5 314 L 451.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 473.5 314 L 473.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 495.5 314 L 495.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 517.5 314 L 517.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 538.5 314 L 538.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 560.5 314 L 560.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 582.5 314 L 582.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 603.5 314 L 603.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 625.5 314 L 625.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 647.5 314 L 647.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 669.5 314 L 669.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 690.5 314 L 690.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path>
					<path d="M 712.5 314 L 712.5 319" fill="none" stroke="#C0D0E0"
						stroke-width="1"></path></g>
					<g class="highcharts-axis" zIndex="7">
					<text x="31" y="317"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end">
					<tspan x="31">0</tspan></text>
					<text x="31" y="262.2"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end">
					<tspan x="31">50</tspan></text>
					<text x="31" y="207.39999999999998"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end">
					<tspan x="31">100</tspan></text>
					<text x="31" y="152.6"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end">
					<tspan x="31">150</tspan></text>
					<text x="31" y="97.79999999999998"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end">
					<tspan x="31">200</tspan></text>
					<text x="31" y="43"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:11px;fill:#666;"
						text-anchor="end">
					<tspan x="31">250</tspan></text></g>
					<path d="M 39 314.5 L 712 314.5" fill="none" stroke="#C0D0E0"
						stroke-width="1" zIndex="7"></path>
					<g class="highcharts-tooltip" zIndex="8" visibility="hidden">
					<rect x="7" y="7" width="0" height="0" rx="5" ry="5" fill="none"
						fill-opacity="0.85" stroke-width="5" isShadow="true"
						stroke="rgb(0, 0, 0)" stroke-opacity="0.05"
						transform="translate(1,1)"></rect>
					<rect x="7" y="7" width="0" height="0" rx="5" ry="5" fill="none"
						fill-opacity="0.85" stroke-width="3" isShadow="true"
						stroke="rgb(0, 0, 0)" stroke-opacity="0.1"
						transform="translate(1,1)"></rect>
					<rect x="7" y="7" width="0" height="0" rx="5" ry="5" fill="none"
						fill-opacity="0.85" stroke-width="1" isShadow="true"
						stroke="rgb(0, 0, 0)" stroke-opacity="0.15000000000000002"
						transform="translate(1,1)"></rect>
					<rect x="7" y="7" width="0" height="0" rx="5" ry="5"
						fill="rgb(255,255,255)" fill-opacity="0.85" stroke-width="2"></rect>
					<text x="12" y="24"
						style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;font-size:12px;padding:0;white-space:nowrap;fill:#333333;"
						zIndex="1">
					<tspan x="12"> </tspan></text></g>
					<g class="highcharts-tracker" zIndex="9"
						transform="translate(39,40)">
					<path
						d="M 0.8548387096774199 30.687999999999988 L 10.85483870967742 30.687999999999988 L 32.564516129032256 59.18399999999997 L 54.2741935483871 67.952 L 75.98387096774194 106.31199999999998 L 97.69354838709678 100.832 L 119.40322580645162 98.63999999999999 L 141.11290322580646 77.81599999999997 L 162.8225806451613 85.488 L 184.53225806451613 39.45599999999999 L 206.24193548387098 63.567999999999984 L 227.95161290322582 96.44799999999998 L 249.66129032258067 119.464 L 271.3709677419355 118.368 L 293.08064516129036 120.56 L 314.7903225806452 113.98399999999998 L 336.5 139.19199999999998 L 358.2096774193549 165.49599999999998 L 379.9193548387097 112.88799999999998 L 401.6290322580645 132.61599999999999 L 423.3387096774194 127.136 L 445.0483870967742 115.07999999999998 L 466.7580645161291 121.65599999999998 L 488.4677419354839 85.488 L 510.1774193548387 66.856 L 531.8870967741935 82.19999999999999 L 553.5967741935484 55.89599999999999 L 575.3064516129033 76.71999999999997 L 597.016129032258 101.928 L 618.7258064516129 60.27999999999997 L 640.4354838709678 83.29599999999999 L 662.1451612903226 150.152 L 672.1451612903226 150.152"
						fill="none" isTracker="true" stroke-opacity="0.000001"
						stroke="rgb(192,192,192)" stroke-width="22" visibility="visible"
						zIndex="1" style=""></path>
					<path
						d="M 0.8548387096774199 259.752 L 10.85483870967742 259.752 L 32.564516129032256 265.23199999999997 L 54.2741935483871 255.368 L 75.98387096774194 250.984 L 97.69354838709678 260.848 L 119.40322580645162 252.07999999999998 L 141.11290322580646 253.176 L 162.8225806451613 245.504 L 184.53225806451613 254.272 L 206.24193548387098 253.176 L 227.95161290322582 252.07999999999998 L 249.66129032258067 255.368 L 271.3709677419355 249.888 L 293.08064516129036 254.272 L 314.7903225806452 258.656 L 336.5 257.56 L 358.2096774193549 260.848 L 379.9193548387097 260.848 L 401.6290322580645 258.656 L 423.3387096774194 245.504 L 445.0483870967742 256.464 L 466.7580645161291 254.272 L 488.4677419354839 246.6 L 510.1774193548387 254.272 L 531.8870967741935 249.888 L 553.5967741935484 248.792 L 575.3064516129033 258.656 L 597.016129032258 255.368 L 618.7258064516129 248.792 L 640.4354838709678 259.752 L 662.1451612903226 265.23199999999997 L 672.1451612903226 265.23199999999997"
						fill="none" isTracker="true" stroke-opacity="0.000001"
						stroke="rgb(192,192,192)" stroke-width="22" visibility="visible"
						zIndex="1" style=""></path>
					<path
						d="M 0.8548387096774199 267.424 L 10.85483870967742 267.424 L 32.564516129032256 257.56 L 54.2741935483871 268.52 L 75.98387096774194 264.136 L 97.69354838709678 266.328 L 119.40322580645162 267.424 L 141.11290322580646 263.04 L 162.8225806451613 261.944 L 184.53225806451613 259.752 L 206.24193548387098 256.464 L 227.95161290322582 258.656 L 249.66129032258067 255.368 L 271.3709677419355 255.368 L 293.08064516129036 263.04 L 314.7903225806452 260.848 L 336.5 261.944 L 358.2096774193549 267.424 L 379.9193548387097 268.52 L 401.6290322580645 266.328 L 423.3387096774194 269.616 L 445.0483870967742 264.136 L 466.7580645161291 260.848 L 488.4677419354839 264.136 L 510.1774193548387 267.424 L 531.8870967741935 261.944 L 553.5967741935484 258.656 L 575.3064516129033 255.368 L 597.016129032258 264.136 L 618.7258064516129 266.328 L 640.4354838709678 259.752 L 662.1451612903226 269.616 L 672.1451612903226 269.616"
						fill="none" isTracker="true" stroke-opacity="0.000001"
						stroke="rgb(192,192,192)" stroke-width="22" visibility="visible"
						zIndex="1" style=""></path>
					<path
						d="M 0.8548387096774199 274 L 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 272.904 L 75.98387096774194 274 L 97.69354838709678 272.904 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 272.904 L 184.53225806451613 270.712 L 206.24193548387098 272.904 L 227.95161290322582 272.904 L 249.66129032258067 274 L 271.3709677419355 272.904 L 293.08064516129036 274 L 314.7903225806452 271.808 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 271.808 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 272.904 L 510.1774193548387 272.904 L 531.8870967741935 271.808 L 553.5967741935484 272.904 L 575.3064516129033 272.904 L 597.016129032258 274 L 618.7258064516129 272.904 L 640.4354838709678 274 L 662.1451612903226 274 L 672.1451612903226 274"
						fill="none" isTracker="true" stroke-opacity="0.000001"
						stroke="rgb(192,192,192)" stroke-width="22" visibility="visible"
						zIndex="1" style=""></path>
					<path
						d="M 0.8548387096774199 274 L 10.85483870967742 274 L 32.564516129032256 272.904 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 272.904 L 162.8225806451613 272.904 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 272.904 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 272.904 L 553.5967741935484 271.808 L 575.3064516129033 274 L 597.016129032258 272.904 L 618.7258064516129 274 L 640.4354838709678 272.904 L 662.1451612903226 274 L 672.1451612903226 274"
						fill="none" isTracker="true" stroke-opacity="0.000001"
						stroke="rgb(192,192,192)" stroke-width="22" visibility="visible"
						zIndex="1" style=""></path>
					<path
						d="M 0.8548387096774199 274 L 10.85483870967742 274 L 32.564516129032256 274 L 54.2741935483871 274 L 75.98387096774194 274 L 97.69354838709678 274 L 119.40322580645162 274 L 141.11290322580646 274 L 162.8225806451613 274 L 184.53225806451613 274 L 206.24193548387098 274 L 227.95161290322582 274 L 249.66129032258067 274 L 271.3709677419355 274 L 293.08064516129036 274 L 314.7903225806452 274 L 336.5 274 L 358.2096774193549 274 L 379.9193548387097 274 L 401.6290322580645 274 L 423.3387096774194 274 L 445.0483870967742 274 L 466.7580645161291 274 L 488.4677419354839 274 L 510.1774193548387 274 L 531.8870967741935 274 L 553.5967741935484 274 L 575.3064516129033 274 L 597.016129032258 274 L 618.7258064516129 274 L 640.4354838709678 274 L 662.1451612903226 274 L 672.1451612903226 274"
						fill="none" isTracker="true" stroke-opacity="0.000001"
						stroke="rgb(192,192,192)" stroke-width="22" visibility="visible"
						zIndex="1" style=""></path></g></svg>
			</div>
		</div>
	</div>

	<div class="filter_params" id="channel_comparision_filter_params">
		<span class="hidden request_data_type">html</span>
		<div class="statsTableHeader">
			<strong>渠道分布对比</strong>
			<div class="fr" style="font-weight:normal;font-size: 13px;">
				<span class="hidden" id="data_type">json</span> 过滤规则： <select
					id="stats_type" name="stats_type" style="margin-right:5px;"><option
						value="installation" selected="selected">新增用户</option>
					<option value="launch">启动用户</option>
				</select> <a href="#" class="choose_channels">选择对比渠道</a>
				<div class="hidden channel_select question_wrap_new_a_box">
					<div class="question_content_top_new_js_a">
						<h3 class="title">
							<span class="fl">选择对比渠道</span> <span class="fr tg_rss"><img
								alt=""
								src="/images/new_ui/report/report_subscribe_close_normal.png">
							</span>
							<div class="clear"></div>
						</h3>
					</div>
					<div class="question_content_center_new_b" style="">
						<ul style="padding:15px;max-height:300px;overflow:auto;">
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a2300193c" checked=""> android
								markets</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e378a66112cf74cd7000005" checked=""> other</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a2300193d" checked=""> bbs</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a23001940" checked=""> market</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a2300193e" checked=""> homepage</li>
							<li style="float:left;margin-right: 20px;"><input
								type="checkbox" name="channels[]"
								value="4e2d1f7e112cf72a2300193f" checked=""> huawei</li>
						</ul>
						<div class="clear"></div>
						<div style="padding:10px;">
							<span class="fl" style="margin: 5px 10px 0 0;"><input
								type="checkbox" class="select_all_btn"> 选择全部</span> <a
								href="#update_channel_comparision_btn"
								class="channel_select_commit certain_btn fr">确定</a> <span
								class="fr" style="margin: 5px 10px 0 0;"><input
								type="checkbox" name="set_default" value="true"> 设置为默认显示</span>
							<div class="clear"></div>
						</div>
					</div>
					<div class="question_content_bottom_new_c"></div>
				</div>
				<input id="update_channel_comparision_btn" type="button"
					class="upbtn" value="&nbsp;更新&nbsp;">
			</div>
			<div class="clear"></div>
		</div>
		<div class="time_select_wrap">
			<div class="statsTableHeader">
				<strong>选择对比时段</strong>

				<div class="selbox">
					<input class="datainp first_date date" type="date"
						name="compared_start_date_1" value="2011-11-07"> <span>到</span>
					<input class="datainp last_date date" type="date"
						name="compared_end_date_1" value="2011-12-07"> VS <input
						class="datainp first_date date" type="date"
						name="compared_start_date_2" value="2012-01-07"> <span>到</span>
					<input class="datainp last_date date" type="date"
						name="compared_end_date_2" value="2012-02-06">
				</div>

				<div class="clear"></div>

			</div>



		</div>
	</div>
	<div id="channel_comparision" style="margin-bottom: 20px;">
		<div class="ccon">
			<table id="channel-stats-table"
				class="infotab table-with-inline-chart new_silver_table"
				style="margin-bottom:10px" width="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tbody>
					<tr>
						<th scope="col" class="new_silver_first_n">渠道名称</th>
						<th scope="col">新增用户比例 ( 2011-11-07 到 2011-12-07 )</th>
						<th scope="col">新增用户比例 ( 2012-01-07 到 2012-02-06 )</th>
						<th scope="col">渠道份额增长</th>
					</tr>
					<tr class="new_silver_odd">
						<td class="new_silver_first_n">android markets</td>
						<td>87.12 <span> %</span>
						</td>
						<td>85.09 <span> %</span>
						</td>
						<td>-2.03 <span> %</span>
						</td>
					</tr>
					<tr class="new_silver_even">
						<td class="new_silver_first_n">other</td>
						<td>5.31 <span> %</span>
						</td>
						<td>9.13 <span> %</span>
						</td>
						<td>3.82 <span> %</span>
						</td>
					</tr>
					<tr class="new_silver_odd">
						<td class="new_silver_first_n">homepage</td>
						<td>7.32 <span> %</span>
						</td>
						<td>5.27 <span> %</span>
						</td>
						<td>-2.05 <span> %</span>
						</td>
					</tr>
					<tr class="new_silver_even">
						<td class="new_silver_first_n">bbs</td>
						<td>0.2 <span> %</span>
						</td>
						<td>0.35 <span> %</span>
						</td>
						<td>0.15 <span> %</span>
						</td>
					</tr>
					<tr class="new_silver_odd">
						<td class="new_silver_first_n">market</td>
						<td>0.05 <span> %</span>
						</td>
						<td>0.15 <span> %</span>
						</td>
						<td>0.1 <span> %</span>
						</td>
					</tr>
					<tr class="new_silver_even">
						<td class="new_silver_first_n">huawei</td>
						<td>0.0 <span> %</span>
						</td>
						<td>0.0 <span> %</span>
						</td>
						<td>0.0 <span> %</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div style="margin-bottom:30px;"></div>
</div>