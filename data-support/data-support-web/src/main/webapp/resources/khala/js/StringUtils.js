function isNotBlank(v){
	if(!v||v==""){
		return false;
	}
	return true;
}
function isBlank(v){
	return !isNotBlank(v);
}
function trim(v){
	
	if(!v) return "";
	return v.trim();
}