const element = document.getElementById("sortOptions");
window.addEventListener("load", loadTable);
element.addEventListener("click", loadTable);


function loadTable() {
    let select = document.getElementById("sortOptions").value;
    let url = "";
    if(select != undefined){
        url = "api/v1/players/"+select;
    }else {
        url = "api/v1/players/all";
    }

    let text = "";
    fetch(url)
        .then(response => response.json())
        .then(function (object) {
            //console.log(object);
            for (const objectKey in object) {
                text += "<tr>"+
                    "<td>"+object[objectKey].name+"</td>"+
                    "<td>"+object[objectKey].num+"</td>"+
                    "</tr>";
            }
            document.getElementById("renderHere").innerHTML = text;
        })
        .catch((err) => {
            text += `No data`;
            document.getElementById("renderHere").innerHTML = text;
        })
}
