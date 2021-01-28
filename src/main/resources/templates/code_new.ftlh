<html>
<head>
    <title>Create</title>
</head>
<body>
<form>
    <textarea id="code_snippet"></textarea><br>
    <input id="time_restriction" type="text" name="time" value="0"><br>
    <input id="views_restriction" type="text" name="views" value="0"><br>
    <button id="send_snippet" type="submit" onclick="event.stopPropagation(); send();">Submit</button>
</form>
<script>
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": document.getElementById("time_restriction").value,
            "views": document.getElementById("views_restriction").value
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status == 200) {
            alert("Success!");
        }
    }
</script>
</body>
</html>
