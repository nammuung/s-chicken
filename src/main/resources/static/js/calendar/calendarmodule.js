import orgChart from "/js/orgChart/orgChart.js";
const searchButton = document.getElementById("searchButton");
			const removeButton = document.getElementById("removeButton");
			const managerIdInput = document.getElementById("managerId");
			const managerNameInput = document.getElementById("managerName");
			const employeeId = document.getElementById("emid").value;
			const title = document.getElementById("title").value;
			const share = document.getElementById("share").value;

			let selectedEmployees = []; // 배열에 선택된 직원을 저장
			let uniqueids = [];


			orgChart.init("orgChart", (data) => {

				if (selectedEmployees.includes(data.id) || uniqueids.includes(data.id)) {
					alert('이미 선택 되었습니다.');
					return;
				}

				if (!selectedEmployees.some(emp => emp.id === data.id || data.id === employeeId)) {
					// 선택된 직원을 배열에 추가
					selectedEmployees.push(data);

					// 선택된 직원들의 이름을 쉼표로 구분하여 입력란에 표시
					updateSelectedEmployees();
				} else {
					// 이미 선택된 직원인 경우 메시지 출력 또는 다른 처리 가능
					alert('이미 선택 되었습니다.');
				}
			});

			searchButton.addEventListener("click", () => {
				const modals = new bootstrap.Modal(document.getElementById("dept-modal"));
				modals.show();
			});


			function updateSelectedEmployees() {
				// 중복된 값을 제거한 배열 생성
				const uniqueNames = [];
				const uniqueEmployees = [];
				for (const employee of selectedEmployees) {
					if (!uniqueNames.includes(employee.name)) {
						uniqueNames.push(employee.name);
						uniqueEmployees.push(employee);
					}
				}


				// 입력란 업데이트
				managerNameInput.value = uniqueNames.join(", ");
				managerIdInput.value = JSON.stringify(uniqueEmployees.map(employee => ({ value: employee.id })));
				console.log(managerIdInput.value);
				// 선택된 직원이 없으면 태그 초기화하지 않음
				if (uniqueNames.length === 0) {
					return;
				}

				if (!tagify) {
					initializeTagify(uniqueNames);  // 태그에 
				} else {
					// 기존 태그를 유지하면서 새로운 태그를 추가
					uniqueNames.forEach(name => {
						if (!tagify.value.some(tag => tag.value === name)) {
							tagify.addTags([{ value: name }]);
						}
					});
				}
			}
			const ids = [];

			const idName = [];


			fetch('/userlist', {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
				},
			})
				.then(response => {
					return response.json();
				})
				.then(data => {
					const names = [];
					// NAME
					data.forEach(department => {
						names.push(department.name); // 부서명을 배열에 추가
						if (department.employees && department.employees.length > 0) {
							department.employees.forEach(employee => {
								names.push(employee.name); // 직원의 이름을 배열에 추가.
							});
						}
					});

					// ID
					data.forEach(department => {
						// 부서명과 부서 ID를 배열에 추가
						ids.push({ id: department.id });
						if (department.employees && department.employees.length > 0) {
							department.employees.forEach(employee => {
								// 직원의 이름과 ID를 배열에 추가
								ids.push({ id: employee.id });
							});
						}
					});
					// ID, NAME
					data.forEach(department => {
						// 부서명과 부서 ID를 배열에 추가
						idName.push({ id: department.id, name: department.name });
						if (department.employees && department.employees.length > 0) {
							department.employees.forEach(employee => {
								// 직원의 이름과 ID를 배열에 추가
								idName.push({ id: employee.id, name: employee.name });
							});
						}
					});
					console.log(ids);
					managerNameInput.value = names.join(", ");

					console.log(names);
					initializeTagify(names);
				})
				.catch(error => {
					console.error('에러났음요:', error);
				});

			let tagify; // 전역 변수로 tagify 선언

			function initializeTagify(names) {
				var input = document.querySelector('textarea[name=tags2]');
				tagify = new Tagify(input, {
					enforceWhitelist: true,
					delimiters: null,
					whitelist: names,
					callbacks: {
						add: function (e) {
							const tagName = e.detail.data.value; // 추가된 태그의 이름 가져오기
							console.log("태그 추가됨: ", e.detail.data);
							console.log("dlq" + tagName);
							// 태그의 이름을 기반으로 해당하는 ID를 찾기
							const foundIds = idName.filter(item => item.name === tagName).map(item => item.id);
							console.log(foundIds);
							// 찾은 ID를 uniqueids 배열에 추가
							uniqueids.push(...foundIds);
							console.log(uniqueids);
							// uniqueids 배열에 있는 ID들을 managerIdInput에 추가
							managerIdInput.value = JSON.stringify(uniqueids.map(id => ({ value: id }))); // ID만 추가
							console.log(managerIdInput.value); // managerIdInput의 값 확


						},
						remove: function (e) {
							console.log("태그 제거됨: ", e.detail.data);
							const removedItem = e.detail.data.value;
							console.log(removedItem);
							// 제거된 태그에 해당하는 ID를 uniqueids 배열에서 제거
							uniqueids = uniqueids.filter(id => !idName.some(item => item.name === removedItem && id === item.id));
							console.log(uniqueids);
							// uniqueids 배열에 있는 ID들을 managerIdInput에 추가
							managerIdInput.value = JSON.stringify(uniqueids.map(id => ({ value: id }))); // 변경된 ID 배열을 다시 할당
							console.log(managerIdInput.value); // managerIdInput의 값 확인
							const index = selectedEmployees.findIndex(employee => employee.name === removedItem);
							if (index !== -1) {
								selectedEmployees.splice(index, 1);
							}
						}
					}
				});
			}