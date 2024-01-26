console.log("boardComment.js in");
console.log(bnoVal);

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText == ''){
        alert("댓글을 입력해주세요");
        cmtText.focus();
        return false;
    } else {
        let cmtData={
            bno: bnoVal,
            writer: document.getElementById('cmtWriter').innerText,
            content: cmtText.value
        };
        //전송
        postCommentToServer(cmtData).then(result=>{
            if(result === '1'){
                alert("댓글 등록 성공~!");
            }
            //뿌리기
            spreadCommentList(bnoVal);
        })
    }
});

async function postCommentToServer(cmtData){
        try {
            const url = "/comment/post";
            const config = {
                method: 'post',
                headers:{
                    'content-type': 'application/json; charset=utf-8' 
                },
                body: JSON.stringify(cmtData)
            };
            const resp = await fetch(url,config);
            const result = await resp.text();
            return result;
        } catch (error) {
            console.log(error);
        }
}

async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch('/comment/'+bno+'/'+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
};

function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result=>{
        console.log(result); //ph cmtList
        const ul = document.getElementById('cmtListArea');
        //댓글 모양대로 뿌리기
        if(result.cmtList.length > 0){
            if(page == 1){ // 1페이지 에서만 댓글 내용 지우기.
                ul.innerHTML = ''; //ul에 원래있던 html 값 지우기
            }
            //댓글을 다시 뿌릴때 기존 값을 삭제하면 안됨
            //1page일 경우에만 삭제해야함
            for(let cvo of result.cmtList){
                let li = `<li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                li += `<div class="ms-2 me-auto">`;
                li += `<div class="fw-bold">${cvo.writer}</div>`;
                li += `${cvo.content}</div>`;
                li += `<span class="badge bg-dark rounded-pill">${cvo.modAt}</span>`;
                li += `<button type="button" class="btn btn-outline-warning mod" data-bs-toggle="modal" data-bs-target="#myModal">e</button>`;
                li += `<button type="button" class="btn btn-outline-danger del">x</button>`;
                li += `</li>`;
                ul.innerHTML += li;
            }
            //페이지 처리
            let moreBtn = document.getElementById('moreBtn');
            //현재 페이지 번호가 전체 페이지 번호보다 작다면
            //아직 나와야 할 페이지가 더 있다면...
            if(result.pgvo.pageNo < result.endPage){
                //숨김 속성 해지
                moreBtn.style.visibility = "visible"; //표시
                //페이지 +1
                moreBtn.dataset.page = page+1;
            }else{
                moreBtn.style.visibility = 'hidden'; //버튼 숨김
            }
        }else{
            let li = `<li class="list-group-item">Comment List Empty</li>`;
            ul = innerHTML
        }
    });
}

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('mod')){
        // 수정
        // 타겟에 가장 가까운 li찾기 : 내 번틍이 포함되어있는 li 찾기
        let li = e.target.closest('li');
        // nextSibling : 같은 부모의 다음 형제 객체를 반환
        let cmtTeext = li.querySelector('.fw-bold').nextSibling; 
        console.log(cmtText);
        // nodeValue : 현재 선택한 노드의 value 반환/
        document.getElementById('cmtTextMod').value = cmtTeext.nodeValue;
        document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);
    }else if(e.target.id=='cmtModBtn'){
        // 모달 수정 버튼
        let cmtDataMod={
            cno: e.target.dataset.cno,
            content: document.getElementById('cmtTextMod').value
        };
        //비동기 통신
        editCommentToServer(cmtDataMod).then(result =>{
            if(result == "1"){ // 수정 성공
                alert("댓글 수정 완료");
                //모달창을 닫기
                document.querySelector('.btn-close').click();
            }else{
                alert("댓글 수정 실패");
                //모달창을 닫기
                document.querySelector('.btn-close').click();
            }
            //수정된 댓글 뿌리기 page=1
            spreadCommentList(bnoVal);
        })
    }else if(e.target.classList.contains('del')){
        // 삭제
    }else if(e.target.id == 'moreBtn'){
        spreadCommentList(bnoVal, parseInt(e.target.dataset.page));
    }
})
    

async function editCommentToServer(cmtDataMod){
    try {
        const url = '/comment/edit';
        const config={
            method:'put',
            headers:{
                'content-type' : 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}