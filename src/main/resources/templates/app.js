
function loadTable() {
    const url = `http://localhost:8090/api/v1/players/all`;
    const method = `GET`;
    let text = "";
    fetch(url, {
        method: method
    })
        .then(response => response.json())
        .then(json => {
            const data = json;
            console.log(data);
            text += "<h5 style='text-align: center; color:"+data.color+";'>Date Range: " + data.date_range + "</h5>" +
                "<p style='text-align: justify; color:"+data.color+";'>" + data.description + "</p>" +
                "<ul id='aztro' style='color:"+data.color+"'>" +
                "<li>Compatibility: " + data.compatibility + "</li>" +
                "<li>Lucky Number: " + data.lucky_number + "</li>" +
                "<li>Lucky Time: " + data.lucky_time + "</li>" +
                "<li>Color: " + data.color + "</li>" +
                "<li>Mood: " + data.mood + "</li>" +
                "</ul>";
            document.getElementById("renderHere").innerHTML = text;
        })
        .catch((err) => {
            text += `No horoscope information available for ${starSign}, sorry...`;
            document.getElementById("two").innerHTML = text;
        })
}
