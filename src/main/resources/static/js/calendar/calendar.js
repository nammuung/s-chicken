(function () {
    $(function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            height: '50vh', // calendar 높이 설정
            expandRows: true, // 화면에 맞게 높이 재설정
            slotMinTime: '08:00', // Day 캘린더에서 시작 시간
            slotMaxTime: '20:00', // Day 캘린더에서 종료 시간
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
            },
            initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
            navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
            editable: true, // 수정 가능?
            selectable: true, // 달력 일자 드래그 설정가능
            nowIndicator: true, // 현재 시간 마크
            dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
            locale: 'ko', // 한국어 설정
            googleCalendarApiKey: "AIzaSyBMcaAD8R_KhHKRl3WMVS0pndILwlEtiDc",
            eventSources: [
                {
                    googleCalendarId: 'ko.south_korea#holiday@group.v.calendar.google.com'
                    , color: 'white'   // an option!
                    , textColor: 'red' // an option!
                }
            ],

            eventAdd: function (obj) { // 이벤트가 추가되면 발생하는 이벤트
                console.log(obj);
                console.log("추가임");
            },
            eventChange: function (obj) { // 이벤트가 수정되면 발생하는 이벤트
                console.log(obj);
                console.log("수정임");
                console.log(obj.event.start);
                console.log(obj.event.end);
                console.log(obj.event.content);
                console.log(obj.event.title);
                console.log(obj.event.id);
                let startday =dayjs(obj.event.start).format('YYYY-MM-DDTHH:mm:ss');
                let endday =dayjs(obj.event.end).format('YYYY-MM-DDTHH:mm:ss');
                console.log(startday);
                console.log(endday);
                $.ajax({
                    url: '/update',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        title: obj.event.title,
                        content: obj.event.content,
                        start: startday,
                        end: endday,
                        id: obj.event.id
                    }),
                    success: function (response) {
                        console.log('수정 성공:', response);
                    },
                    error: function (error) {
                        console.error('수정 실패:', error);
                    }
                });
            },
            eventRemove: function (obj) { // 이벤트가 삭제되면 발생하는 이벤트
                console.log(obj);
                console.log("삭제임");
            },
            eventClick: function (obj) {
                console.log(obj)
                console.log("클릭임");
                console.log(obj.event.start);
                console.log(obj.event.end);
                $.ajax({
                    url: '/detail',
                    type: 'GET',
                    contentType: 'application/text',
                    data: {
                        id: obj.event.id

                    },
                    success: function (response) {
                        console.log('수정 성공:', response);
                        console.log(response.id);
                        console.log(response.title);
                        console.log(response.content);
                        console.log(response.share);
                        console.log(response.start);
                        console.log(response.end);
                         $('#title2').val(response.title);
                            $('#content2').val(response.content);
                            $('#share2').val(response.depname+"/"+response.cname+"/"+response.name);

                            // Date formatting might be necessary depending on your backend's date format
                            $('#start2').val(response.start);
                            $('#end2').val(response.end);

                            // Show the modal after setting the values
                            $('#exampleModal').modal('show');
                        
                    },
                    error: function (error) {
                        console.error('수정 실패:', error);
                    }
                });

                $('#exampleModal').modal('show');
                calendar.unselect();
            },
            select: function (arg) {
                $('form').each(function() {this.reset();});
                $('#eventModal').modal('show');
                // 부트스트랩 모달 열기

                var startDate = arg.start.toISOString().slice(0, 10); // 선택된 날짜의 날짜 부분만 추출
                var endDate = arg.end ? arg.end.toISOString().slice(0, 10) : startDate; // 종료 날짜가 있으면 그 날짜를 선택, 없으면 시작 날짜와 같은 날짜 선택

                // 시작 날짜에 하루를 더하여 설정
                var nextDay = new Date(arg.start);
                nextDay.setDate(nextDay.getDate() + 1);
                var nextDayISO = nextDay.toISOString().slice(0, 10);

                $('#start').val(nextDayISO + 'T' + getCurrentTime()); // 시작 날짜 입력란에 선택된 날짜와 현재 시간 값 설정
                $('#end').val(endDate + 'T18:00'); // 종료 날짜 입력란에 선택된 날짜 값 설정

                // 현재 시간을 HH:mm 형식으로 반환하는 함수
                function getCurrentTime() {
                    var now = new Date();
                    var hour = now.getHours().toString().padStart(2, '0'); // 시간을 두 자리로 변환하고 앞에 0을 채움
                    var minute = now.getMinutes().toString().padStart(2, '0'); // 분을 두 자리로 변환하고 앞에 0을 채움
                    return hour + ':' + minute; // 시간과 분을 합쳐서 반환
                }


                // 모달에서 이벤트 제목 입력 후 저장 버튼 클릭 시
                $('#saveEventBtn').off('click').click(function () {
                    var employeeId = $('#emid').val();
                    var content = $('#content').val();
                    if (content.trim() === "") {
                        alert("내용이 입력되지 않았습니다.")
                        return;
                    }
                    var title = $('#title').val(); // 모달의 이벤트 제목 입력란에서 제목 가져오기
                    if (title.trim() === "") {
                        alert("제목을 입력하지 않았습니다.");
                        return;
                    }

                    if (title) {
                        calendar.addEvent({
                            title: title,
                            start: arg.start,
                            end: arg.end,
                            allDay: arg.allDay

                        });

                        // 저장 버튼 클릭 시 AJAX를 통해 데이터를 서버에 전송
                        let start = document.getElementById("start").value;
                        let end = document.getElementById("end").value;
                        const employeeId = document.getElementById("emid").value;
                        console.log(employeeId + "로그임");
                        /* if (managerId && !managerId.value) {
                            console.log("managerId.value가 비어있습니다.");
                            managerId.value = employeeId; // managerId.value를 employeeId로 설정
                        } */

                        // 결과를 콘솔에 출력


                        let startday =dayjs(start).format('YYYY-MM-DDTHH:mm:ss');
                        let endday =dayjs(end).format('YYYY-MM-DDTHH:mm:ss');
                        console.log(managerId.value + " 매니저");
                        $.ajax({
                            url: '/insert',
                            type: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify({
                                title: title,
                                content: content,
                                start: startday,
                                end: endday,
                                employeeId: managerId.value
                            }),
                            success: function (response) {
                                console.log('저장 성공:', response);
                            },
                            error: function (error) {
                                console.error('저장 실패:', error);
                            }
                        });
                    }
                    calendar.unselect();

                    $('#eventModal').modal('hide'); // 모달 닫기
                });
            },

        });
        // 캘린더 랜더링
        $(document).ready(function () {
            // 서버에서 데이터를 가져오는 AJAX 요청
            $.ajax({
                url: 'http://localhost/list',
                type: 'GET',
                success: function (data) {
                    // 가져온 데이터를 풀캘린더에 추가
                    data.forEach(function (eventData) {
                        console.log(eventData.id + "LLLLL");
                        calendar.addEvent({
                            id: eventData.id, // 이벤트 ID
                            title: eventData.title, // 이벤트 제목
                            start: eventData.start, // 이벤트 시작 날짜
                            end: eventData.end, // 이벤트 종료 날짜
                            // 기타 이벤트 속성 등을 추가할 수 있습니다.
                        });
                    });
                },
                error: function (error) {
                    console.error('데이터를 가져오는 데 실패했습니다:', error);
                }
            });
        });
        calendar.render();
    });
})();
$(document).ready(function () {
    // 종일 체크박스 클릭 이벤트 처리
    $('#alltime').change(function () {
        if (this.checked) {
            // 종일 옵션이 체크되었을 때
            $('#end').val(getEndDateAllDay()); // 종료 시간을 23:59:59로 설정
        } else {
            // 종일 옵션이 해제되었을 때
            $('#end').val(getEndDateNormal()); // 종료 시간을 기존의 18:00으로 설정
        }
    });

    // 종일 옵션 체크 시 종료 시간을 23:59:59로 반환하는 함수
    function getEndDateAllDay() {
        var endDate = $('#start').val().slice(0, 10); // 시작 날짜를 가져와서 ISO 형식으로 변환
        return endDate + 'T23:59:59'; // 종료 시간을 23:59:59로 설정하여 반환
    }

    // 종일 옵션 해제 시 종료 시간을 18:00으로 반환하는 함수
    function getEndDateNormal() {
        var endDate = $('#start').val().slice(0, 10); // 시작 날짜를 가져와서 ISO 형식으로 변환
        return endDate + 'T18:00'; // 기존의 종료 시간(18:00)으로 설정하여 반환
    }
});
