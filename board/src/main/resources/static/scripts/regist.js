
	 function openModal(error){
			var modal=new bootstrap.Modal(document.getElementById('staticBackdrop'));
			document.getElementById('modal_message').innerHTML=error;
			modal.show();
		}
        function EnNumInput(e)  {
  		e.value = e.value.replace(/[^A-Za-z]/ig, '')
  		}
  		function lengthCheck(target,length){
			  var str=target.value;
			  var targetLength=str.length;
			  var totalbyte=0;
			  var len=0;
			  var char="";
			  var tempstr="";
			  
			  for(var i=0; i<targetLength; i++){
				  char=str.charAt(i);
				  if(escape(char).length >4 ){
					  totalbyte+=2;
				  }else{
					  totalbyte++;
				  }
			  }
			  if(totalbyte>length){
				  openModal('한글 이름은 최대 10글자까지만 입력 가능합니다.');
				  //alert("한글 이름은 최대 10글자까지만 입력 가능합니다.");
				  target.value=null;
			  }
		  }
		function phoneformat(target){
			  var number=target.value.replace(/\D/g,'');
			  var formatted=number.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
			  target.value=formatted;
			}
		function emailChecker(target){
			var email=target.value.toLowerCase().endsWith('.com');
      		var achecker=target.value.includes('@');
      		if(!email||!achecker){
			openModal('입력하신 이메일이 이메일 형식이 아닙니다. 다시 입력해주세요');
       		target.value=null;
      			}
			}
		function gitFormatter(target){
			var gitid=target.value;
			var formatted="https://github.com/"+gitid;
			target.value=formatted;
		}
		document.getElementById("regit_submit").addEventListener('click', function(event){
			var inputID = document.getElementById('input_id').value;
			console.log(inputID);
			var inputPW = document.getElementById('input_pw').value;
			var inputName = document.getElementById('name').value;
			var inputPhone = document.getElementById('phone').value;
			var inputgitAdd = document.getElementById('git-address').value;
			var inputEmail = document.getElementById('email').value;
			
			if(inputID==null||
			inputPW==null||
			inputName==null||
			inputPhone==null||
			inputgitAdd==null||
			inputEmail==null	
			){
				event.preventDefault();
				openModal('필수 입력 정보가 다 입력되지 않았습니다. 확인해주세요');
			}
		})