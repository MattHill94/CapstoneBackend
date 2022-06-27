window.addEventListener("load", loadTable);

function loadTable() {
    const url = `/api/v1/players/all`;
    let text = "";
    fetch(url)
        .then(response => response.json())
        .then(function (object) {
            console.log(object);
                 text += "<tr>"+
                     "<td>"+data[dataKey].name+"</td>"
                 "<td>"+data[dataKey].num+"</td>"
                 "</tr>";


            document.getElementById("renderHere").innerHTML = text;
        })
        .catch((err) => {
            text += ` `;
            document.getElementById("renderHere").innerHTML = text;
        })
}
