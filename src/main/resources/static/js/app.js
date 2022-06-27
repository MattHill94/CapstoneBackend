const element = document.getElementById("sortOptions");

/*Loads the data to the table on the data page by calling
the loadTable method below on initial load and reloads*/
window.addEventListener("load", loadTable);

/*Loads the data to the table on the data page by calling
the loadTable method when an option from the select combobox
with id "sortOptions" is clicked*/
element.addEventListener("click", loadTable);

/* The method first checks if was called by a click action from the comboBox
* if true, it executes the action requested i.e. ASC, DESC or revert to original state
* else, it loads data in original order*/
function loadTable() {
    let select = document.getElementById("sortOptions").value;
    let text = "";
    let url = "";

    if(select != undefined){
        // it means that an option from the select is clicked,
        // therefore we take the value of the element clicked and append
        // it to the end of "api/v1/players/" to complete the url.
        // The three possible values are: "sortASC", "sortDESC" and "all".
        url = "api/v1/players/"+select;
    }else {
        //we assign the url variable link to the original format
        url = "api/v1/players/all";
    }
    //Data is fetched by calling methods from the PlayerList class
    // and then rendered in element with id "renderHere"
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
            //catches err and displays "No data available"
            text += `No data available...`;
            document.getElementById("renderHere").innerHTML = text;
        })
}
