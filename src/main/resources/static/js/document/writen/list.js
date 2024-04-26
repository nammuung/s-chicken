
function screen(){
    // 현재 화면의 너비와 높이를 가져옵니다.
    let screenWidth = window.screen.width;
    let screenHeight = window.screen.height;

    // 창의 너비와 높이를 설정합니다.
    let widthPercentage = 50; // 화면 너비의 50%
    let heightPercentage = 100; // 화면 높이의 100%
    let width = (screenWidth * widthPercentage) / 100;
    let height = (screenHeight * heightPercentage) / 100;

    // 창의 위치를 계산하여 화면 중앙에 위치시킵니다.
    let left = (screenWidth - width) / 2;
    let top = (screenHeight - height) / 2;

    // 창의 크기와 위치를 설정합니다.
    return `width=${width}, height=${height}, left=${left}, top=${top}`;
}

function openbonus(id) {
	let options = screen();
    let relativePath = '/document/writenList/writenBonus?id='+id; // 문서의 상대 경로를 설정합니다.

    window.open(relativePath, '_blank', options);
}

const categori = document.querySelectorAll(".BOARDCATEGORY");

const cateList = document.getElementById("cateList");

cateList.addEventListener("click",(e)=>{	
	for(let i = 0; i < categori.length ; i++){
		categori[i].classList.remove("active");
	}
	e.target.classList.add("active");
	console.log(e.target.dataset.category)
	let data = {
		categori : e.target.dataset.category
	}
	
	console.log(data)
	fetch("/document/document",{
		method:"get",
		body:data
	}).then(r=>console.log(r))
}) 