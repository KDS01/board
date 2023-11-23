const idreg=/^[a-z0-9]{3,20}$/i;
const pwReg=/^(?=.*[a-zA-Z])(?=.*[!@#$^&])(?=.*[0-9]).{10,30}$/;

function checkId(form){
	let msg="";
	if(!idreg.test(form.userId.value)){
		if(form.userId.value.length <3||form.userId.value.length>20){
			msg="아이디의 길이는 3~20으로 해주세요"
			}else{
			msg="아이디는 영어와 숫자만 적을 수 있습니다."
			}
		}
	return msg;
}

function checkPW(form){
	let msg="";
		if(!pwReg.test(form.password.value)){
			if(form.password.value.length<10||form.password.value.length>30){
				msg="비밀번호는 10자 이상 30자 미만입니다";
			}else{
				msg="비밀번호는 대소문자,숫자,특수문자를 포함해야합니다";
				}
			}
	return msg;
	
}
function checkMsg(msg,e){
	if(msg){
		e.preventDefault();
		document.getElementById('modal.container').classList.add('on');
		document.getElementById('modal-msg').innerHTML=msg;
		return true;
	}
	return false;
}

document.getElementById('login_box').onsubmit=(e)=>{
	let msg = checkId(e.target);
	if(!msg){
		msg=checkPW(e.target)
		}
	checkMsg(msg,e);
		
}