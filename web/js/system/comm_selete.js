
/**
 * 根据传入的父节点的值得到相应子节点构建下拉列表数据
 * @param sel_id select下拉框id值
 * @param sel_val select下拉框数据来源值(表中父项的值或者是某一类集合值的归属值)
 * createSel('jygm','A04');初始化经营规模的下拉框数据 A04为经营规模的父节点
 *   
 */
function createMenuSel(sel_id, sel_val) {
	$("#" + sel_id).empty(); // 清空SELECT控件
	$("#" + sel_id).append("<option value=\"-1\">请选择</option>");
	$.post(basepath + "/model/getMenuSel.action", {
		pId : sel_val,
	}, function(data) {
		$.each(data.menuList, function(key, value) {
			$("#" + sel_id).append(
					$("<option></option>").attr("value", value.id).text(
							value.modName));
		});
	});
}
