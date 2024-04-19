import oc from "/js/orgChart/orgChart.js"

const getaprrove = document.getElementById("getaprrove");
const notimprrove = document.getElementById("notimprrove");
let list = document.getElementById("note-message-selected-list");
		let arr = [];
		
let selectedData;



function hyuga(){
    
    const vacation = document.getElementById("vacation");
    const yoen_sel = document.getElementById("yoen_sel");
    const ban_sel = document.getElementById("ban_sel");
    

    if(vacation.value == "yoen"){        
        yoen_sel.style.display="table-row";
        ban_sel.style.display="none";
    }else if (vacation.value == "ban"){
        ban_sel.style.display="table-row";
        yoen_sel.style.display="none";
    }else{
        yoen_sel.style.display="none";
        ban_sel.style.display="none"
    }
}
    ClassicEditor
        .create(document.querySelector('#editor'))
        .then(editor => {
            editor.editing.view.change(writer => {
                writer.setStyle('height', '20vh', editor.editing.view.document.getRoot());
            });
        })
        .catch(error => {
            console.error(error);
        });

  $(document).ready(function(){
    function adjustSize() {
      var windowHeight = $(window).height();
      var windowWidth = $(window).width();
      var modalWidth = windowWidth * 1; // 화면 너비의 50%
      var modalHeight = windowHeight * 0.8; // 화면 높이의 80%
      
      $('#modalContent').css('width', modalWidth);
      $('#modalContent').css('max-width', 'none'); // 최대 너비 제한 해제
      $('#modalContent').css('height', modalHeight);
    }

    // 최초 로드시 크기 조정
    adjustSize();

    // 윈도우 크기가 변경될 때마다 크기 조정
    $(window).resize(function(){
      adjustSize();
    });
    oc.init("org-chart", onSelectOrgChart, 'person', false);
	// 'person' 사람만선택 , false : chart가 접혀있는상태
	
	
	function onSelectOrgChart(data){
		console.log(data)
	
		
		selectedData=data;
		
					
			//자료구조 사전구조 tuple,hashmap			
			
			//배열에 넣고싶은 사번 있는지 검사,있으면 패스,없으면 추가	
	}
    

  });



  
  getaprrove.addEventListener("click",()=>{
            console.log(selectedData.name)
			let temp = false;
            if(arr.length == 3){
                alert("서명은 3명까지입니다")
                return;					
            }
            let cut = selectedData.name.split(' ');
            let level = cut[0];
            let name = cut[1];
			for(let i = 0 ; i <arr.length ;i++){
				
				if(arr[i].id==selectedData.id){
					temp = true;				
				}
			}
			
			if(!temp){
				arr.push({id:selectedData.id,직책:selectedData.name});
                //li문을 i의 갯수만큼 넣기
                console.log(arr)
                let textin = `
                    <li class="list-group-item" id="${selectedData.id}" data-level="${level}" data-name="${name}">
                        <i class="bi bi-arrows-expand handle"></i>
                    직책:${selectedData.name}
                    </li>
                    ` ;                
                list.innerHTML +=textin;        
            }

		})
let approveSelectElement

list.addEventListener("click",(e)=>{
    // e.target의 아이디를 기억 
   //(e) 넣기 => 이벤트로 대상 체크 하고 -> 배열에서 삭제 -> 요소삭제
    console.log(e.target.id);
    approveSelectElement = e.target;
    
})
notimprrove.addEventListener("click", () => {
    const id = approveSelectElement.id;
    for(let i = 0 ; i < arr.length ; i++){
        if(arr[i].id == id){
            console.log(arr)
            arr.splice(i,1);
            console.log(arr)
            break;
            //list.removeAttribute(e.target);
        }
    }
    approveSelectElement.remove();
});
new Sortable(list,{
    handle:'.handle',
    animation: 150
});

const register = document.getElementById("resiter");

register.addEventListener("click",()=>{
    register.removeAttribute("data-dismiss");
    

    let signMemberWraps = [...document.querySelectorAll('.sign_member_wrap')].splice(1,3);
	let liCount = list.querySelectorAll('li');
    let first = 3-liCount.length;

    for(let i = 0 ; i < 3; i++){
        signMemberWraps[i].querySelector('.sign_member .sign_rank').innerHTML="";
        signMemberWraps[i].querySelector('.sign_member #name').innerHTML="";
    }


	  for(let i = 0 ;i < liCount.length ; i++){
        

		signMemberWraps[i+first].querySelector('.sign_member .sign_rank').innerHTML=liCount[i].getAttribute('data-level')
		signMemberWraps[i+first].querySelector('.sign_member #name').innerHTML=liCount[i].getAttribute('data-name')
		}
        
        if(liCount.length ==0){
            console.log(liCount.length)
            alert("서명자는 최소 1명 이상입니다.")
        }else{
            
            register.setAttribute("data-dismiss","modal")
            register.dispatchEvent(new Event('click'));
            
        }
    
    
})

