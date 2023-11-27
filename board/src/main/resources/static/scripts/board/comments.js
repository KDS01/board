const commentListElem = document.getElementById('comment-list');
const getList = async () => {
    const list = (
        await axios.get(`/comments?boardId=${commentListElem.dataset.boardId}`)
    ).data;
    console.log(list);

    list.forEach((item) => {
        const tempLiElem = document.createElement('li');
        tempLiElem.innerHTML = `${item.content} - ${item.user_id}`;
        const tempOlElem = document.createElement("ul");
        item.children.forEach((child) => {
            const temp2 = document.createElement('li');
            temp2.innerHTML = `${child.content} - ${child.user}`;
            tempOlElem.append(temp2);
        });
        tempLiElem.append(tempOlElem);

        commentListElem.append(tempLiElem);
    });
};

getList();