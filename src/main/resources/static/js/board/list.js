


Array.prototype.slice.call(document.querySelector("select"))
    .forEach(options => {
        if(options.value == '${pager.kind}') options.selected = true;
    })

const prev = document.getElementById("prev");
const prev_val = document.getElementById("prev_val");
    
    if(prev_val.value == 1){
        prev.classList.add("disabled");
    }

const next = document.getElementById("next");
const next_val = document.getElementById("next_val");

console.log(next_val.value);
    if(next_val.value){
        next.classList.add("disabled");
    }
    
const important = document.getElementById("important");
const imp_val = document.getElementById("important_val");
	console.log(imp_val.value)
	
	if(imp_val.value == 'true'){
		important.style.backgroundColor = 'red';
	}

  
