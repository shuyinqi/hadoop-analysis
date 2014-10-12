/* 
*  title:   about date input and handle
*  rely:    jquery.tools 
*/

// date input localization
$.tools.dateinput.localize("zh_cn",  {
    months:         '一月,二月,三月,四月, 五月,六月,七月,八月,九月,十月,十一月,十二月',
    shortMonths:   '一,二,三,四,五,六,七,八,九,十,十一,十二',
    days:          '星期日,星期一,星期二,星期三,星期四,星期五,星期六',
    shortDays:     '日,一,二,三,四,五,六'
 });

jQuery(function(){
  jQuery(":date").dateinput({lang: 'zh_cn', format: 'yyyy-mm-dd',selectors: true,firstDay:1});
});
 