var dropArea = document.getElementById('dropArea');
var uploadForm = document.getElementById('uploadForm');
var currentSVGName = "";
var selectedSVG;
var itemsInFridge = [];
const shakingImage = document.getElementById("shakingImage");

// Prevent default behavior when files are dragged and dropped onto the drop area
dropArea.addEventListener('dragenter', preventDefault, false);
dropArea.addEventListener('dragleave', preventDefault, false);
dropArea.addEventListener('dragover', preventDefault, false);
dropArea.addEventListener('drop', handleDrop, false);

// Open file dialog when the drop area is clicked
dropArea.addEventListener('click', function() {
    console.log("FRIDGE CLICKED")
});

function preventDefault(e) {
    e.preventDefault();
    e.stopPropagation();
}

function handleDrop(e) {
    preventDefault(e);

    console.log(currentSVGName + " DROPPED ONTO FRIDGE!")

    // Get the item file name from the "src" attribute
    var itemName = currentSVGName.split(".").shift();
    itemsInFridge.push(itemName);
    selectedSVG.style.opacity = 0.5;
}

// Add event listener to each SVG item
var svgItems = document.querySelectorAll(".svg-item");
svgItems.forEach(function(svgItem) {
    svgItem.addEventListener("mousedown", function(e) {
        // Get the SVG file name when the SVG item is clicked down
        var svgFileName = e.target.src.split("/").pop();
        currentSVGName = svgFileName;
    });
});

shakingImage.addEventListener("dragenter", () => {
  shakingImage.classList.add("dragged");

  setTimeout(() => {
    shakingImage.classList.remove("dragged");
  }, 1000); // Remove "dragged" class after 1 second
});



function getSVGElement(svgItem) {
    selectedSVG = svgItem;
}


// AJAX Call to backend
document.getElementById("findRecipesLink").addEventListener("click", function(event) {
  event.preventDefault(); // Prevents the default navigation behavior

  // Make an AJAX request to your server endpoint
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "/findRecipe", true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      // Handle the response from the server if needed
      console.log("Response from server:", xhr.responseText);
    }
  };
  xhr.send();
});