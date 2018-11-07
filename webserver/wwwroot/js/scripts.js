function loadFileAsText() {
  const fileToLoad = document.getElementById("fileToLoad").files[0];

  const fileReader = new FileReader();
  fileReader.onload = function (fileLoadedEvent) {
    let textFromFileLoaded = fileLoadedEvent.target.result;
    document.getElementById("fileOutput").innerHTML += textFromFileLoaded;
    fetch('http://localhost:8080/iea/rs/files/uploadtext', {
      method: 'POST',
      headers: {
        "Content-Type": "text/plain"
      },
      body: textFromFileLoaded
    }).then(
      response => console.log(response)
    ).catch(
      error => console.log(error)
    )
  };

  fileReader.readAsText(fileToLoad, "UTF-8");
}

//https://stackoverflow.com/a/36082038
//https://stackoverflow.com/a/40580004

