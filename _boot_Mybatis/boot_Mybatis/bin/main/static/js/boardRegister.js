console.log("board register in~!");

document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('files').click();
});

const regExp = new RegExp("\.(exe|sh|bat|js|dll|msi)$"); //실행파일 막기
const regExpImg = new RegExp("\.(jpg|jpeg|png|bmp|gif)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    } else {
        return 1;
    }
};

document.addEventListener('change',(e)=>{
    if(e.target.id == 'files'){
        const fileObject = document.getElementById('files').files; //multiple이기에 배열로 들어옴
        console.log(fileObject);
        document.getElementById('regBtn').disabled = false; //활성화(비활성화된거 풀어줘야함 지 혼자 못품)

        const div = document.getElementById('fileZone');
        div.innerHTML = ''; //이전 업로드했던 파일들이 있다면 제거
        
        let ul = `<ul class="list-group list-group-flush">`;
       
       let isOk = 1; //여러 파일에 대한 값 확인에 대한 값 
       //배열일때는 of...
        for(let file of fileObject){
            let validResult = fileValidation(file.name, file.size); //내 파일
            isOk *= validResult; //하나씩 모든 파일에 대한 확인, 전체 파일
            ul += `<li class="list-group-item">`;
            ul += `<div class="ms-2 me-auto">`;
            ul += `${validResult ? '<div class="fw-bold">업로드 가능' : '<div class="fw-bold text-danger">업로드 불가능'}</div>`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge bg-${validResult ? 'success':'danger'} rounded-pill">${file.size}Byte</span>`;
            ul += `</li></ul>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul; //다 추가해서 넣을거기때문에 누적 안해도됨

        if(isOk == 0){
            //파일 중 valid 결과에 맞지 않는 값이 있다면...
            document.getElementById('regBtn').disabled = true; //비활성화
        }
    }
});

