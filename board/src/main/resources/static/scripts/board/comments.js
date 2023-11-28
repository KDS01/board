const commentListElem = document.getElementById('comment-list');
const commentForm = document.getElementById('board-comment');
const getList = async () => {
    const data = (
        await axios.get(`/comments?boardId=${(commentListElem.dataset.boardId)}
        &start=${commentListElem.children.length}`)
    ).data;
    console.log(data); 
    if(data.end){
		document.getElementById('add-comment-btn').outerHTML="";
	}
	setList(data.list,commentListElem);
};
	
function setList(list,parentElem){
    list.forEach((item,idx) => {
        const tempLiElem = document.createElement('div');
        const tempArrowElem=document.createElement("img");
        tempArrowElem.classList.add('recomment-img');
        tempArrowElem.src="/imgs/right-down.png";
        tempLiElem.append(tempArrowElem);
        tempLiElem.classList.add('comment-item');
        tempLiElem.innerHTML += `${item.content} <span class="comment-user-name">${item.userName}</span> / ${new Date(item.createAt)}`;
        
        if(commentForm){
			const tempButtonElem=document.createElement('button');
			tempButtonElem.innerHTML='댓글';
			tempButtonElem.classList.add('btn','btn-primary');
			tempLiElem.append(tempButtonElem);
			const tempFormElem=document.createElement('form');
			tempFormElem.action='/comments/add'
			tempFormElem.method='post';
			tempLiElem.append(tempFormElem);
			const tempUserIdElem=document.createElement('input');
			tempUserIdElem.type='hidden';
			tempUserIdElem.name="user_id";
			tempUserIdElem.value=commentForm['user_id'].value;
			tempFormElem.append(tempUserIdElem);
			const tempCommentIdElem =document.createElement('input');
			tempCommentIdElem.type='hidden';
			tempCommentIdElem.name="comment_id";
			tempCommentIdElem.value=item.id;
			tempFormElem.append(tempCommentIdElem);
			const tempContentElem=document.createElement('input');
			tempContentElem.name='content';
			tempFormElem.append(tempContentElem);
			tempButtonElem.onclick=()=>{
				tempFormElem.classList.toggle('on');
				tempButtonElem.classList.toggle('btn-primary');
				tempButtonElem.classList.toggle('btn-dark');
				if(tempButtonElem.className.indexOf('btn-primary')>-1){
					tempButtonElem.innerHTML='댓글';
				}else{
					tempButtonElem.innerHTML='취소';
					tempContentElem.focus();
				}	
		}
		const tempButtonOnElem=document.createElement('button');
		tempButtonOnElem.classList.add('btn','btn-primary');
		tempButtonOnElem.innerHTML="댓글 추가";
		tempFormElem.append(tempButtonOnElem);
	}
	const tempOlElem=document.createElement('div');
	tempOlElem.classList.add('comment-list');
	setList(item.children, tempOlElem);
	tempLiElem.append(tempOlElem);
	parentElem.append(tempLiElem);
	});
}


getList();

document.getElementById('add-comment-btn').onclick=()=>{
	getList();
}