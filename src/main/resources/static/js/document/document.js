

function screen(){
    // 현재 화면의 너비와 높이를 가져옵니다.
    let screenWidth = window.innerWidth;
    let screenHeight = window.innerHeight;
	
    // 창의 너비와 높이를 설정합니다.
    let widthPercentage = 60; // 화면 너비의 50%
    let heightPercentage = 100; // 화면 높이의 100%
    let width = (screenWidth * widthPercentage) / 100;
    let height = (screenHeight * heightPercentage) / 100;

    // 창의 위치를 계산하여 화면 중앙에 위치시킵니다.
    let left = (screenWidth - width) / 2;
    let top = (screenHeight - height) / 2;

    // 창의 크기와 위치를 설정합니다.
    return `width=${width}, height=${height}, left=${left}, top=${top}`;
}

function openbonus() {
	let options = screen();
	console.log(options)
    let relativePath = '/document/exList/bonus'; // 문서의 상대 경로를 설정합니다.

    window.open(relativePath, '_blank', options);
}
function openretire() {
	let options = screen();
    let relativePath = '/js/document/retire/퇴사신청서.html'; // 문서의 상대 경로를 설정합니다.

    window.open(relativePath, '_blank', options);
}
function openvaca() {
	let options = screen();
    let relativePath = '/document/exList/vacation'; // 문서의 상대 경로를 설정합니다.

    window.open(relativePath, '_blank', options);
}