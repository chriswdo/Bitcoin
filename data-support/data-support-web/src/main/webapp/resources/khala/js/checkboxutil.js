

/**
* selectAlls别名
*/
function selectAll(selectCheck, ob) {
	return selectAlls(selectCheck, ob);
}

/**
* 全选功能
* 用法：	
*	<input type="checkbox" id="selectAll" name="全选" value="-1"
*		onclick="javacript:selectAlls(this, document.all.item('selectIDs'))">
*/
function selectAlls(selectCheck, ob) {
	if (ob) {
		if (selectCheck.checked) {
			if (ob.length) {
				for (var i = 0; i < ob.length; i = i + 1) {
					ob[i].checked = true;
				}
			} else {
				ob.checked = true;
			}
		} else {
			if (ob.length) {
				for (var i = 0; i < ob.length; i++) {
					ob[i].checked = false;
				}
			} else {
				ob.checked = false;
			}
		}
	}
}

/**
* 是否只选择了一条记录
* @param ob select控件
*/
function isCheckedOnlyOne(ob) {
	return isCheckedNum(ob) == 1;
}

/**
* 是否至少选择了一条记录
* @param ob select控件
*/
function isCheckedMulti(ob) {
	return isCheckedNum(ob) != 0;
}

/**
* 检查多选对象所选择的个数
*/
function isCheckedNum(ob) {
	var checkedNum = 0;
	if (ob) {
		if (ob.length) {
			for (var i = 0; i < ob.length; i++) {
				if (ob[i].checked) {
					checkedNum = checkedNum + 1;
				}
			}
		} else {
			if (ob.checked) {
				checkedNum = 1;
			}
		}
	}
	return checkedNum;
}

//还没有使用到的！
function isChecked(ob) {
	var bool = false;
	if (ob) {
		if (ob.length) {
			for (var i = 0; i < ob.length; i++) {
				if (ob[i].checked) {
					bool = true;
					break;
				}
			}
		} else {
			if (ob.checked) {
				bool = true;
			}
		}
	}
	return bool;
}

/**
* 返回多选checkbox里被选中的值
* @param ob 多选框控件
* @param splitChar 指定返回值之间的分隔符，不指定时默认为":"分号
*/
function checkValue(ob, splitChar) {
	var splitChar_now;
	if (splitChar == "undefined" || splitChar == null) {
		splitChar_now = ":";	//不指定时默认为":"分号
	} else {
		splitChar_now = splitChar;
	}
	str = null;
	if (ob) {
		if (ob.length) {
			for (var i = 0; i < ob.length; i++) {
				if (ob[i].checked) {
					if (str == null) {
						str = ob[i].value;
					} else {
						str = str + splitChar_now + ob[i].value;
					}
				}
			}
		} else {
			if (ob.checked) {
				str = ob.value;
			}
		}
	}
	return str == null ? "" : str;
}

