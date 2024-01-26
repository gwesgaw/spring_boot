console.log("board.detail.js.in");
document.getElementById('listBtn').addEventListener('click',()=>{
    // location.href="/board/list";
    // location.replace("/board/list");
    const delForm = document.getElementById('delForm');
    delForm.bno.remove();
    delForm.setAttribute('action','/board/list');
    delForm.setAttribute('method','get');
    delForm.submit();
});

document.getElementById('delBtn').addEventListener('click',()=>{
    document.getElementById('delForm').submit();
});

document.getElementById('modBtn').addEventListener('click',()=>{
    document.getElementById('title').readOnly = false;
    document.getElementById('content').readOnly = false;

    let modBtn = document.createElement('button');
    modBtn.setAttribute('type','submit');
    modBtn.classList.add('btn','btn-warning');
    modBtn.innerText = "Submit";
    
    document.getElementById('modForm').appendChild(modBtn);
    document.getElementById('modBtn').remove();
    document.getElementById('delBtn').remove();

});