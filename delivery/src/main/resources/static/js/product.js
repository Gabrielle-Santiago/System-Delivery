document.querySelector("form").addEventListener("submit", async function (e) {
  e.preventDefault();

  let imgName = "";

  async function uploadFiles() {
    const img = document.getElementById("file").files[0];
    const formData = new FormData();
    formData.append("file", img);

    const response = await fetch("/api/upload", {
      method: "POST",
      body: formData
    });

    const result = await response.json();
    return result.fileName;
  }

  try {
    imgName = await uploadFiles();

    const productData = {
      name: this.name.value,
      valueProduct: parseFloat(valueProduct.value),
      describe: describe.value,
      imgName: imgName 
    };

    const response = await fetch("/admin/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(productData)
    });

    if (response.ok) {
      alert("Produto cadastrado com sucesso!!");
    } else {
      alert("Ocorreu um erro ao cadastrar o produto.");
    }

  } catch (error) {
    console.error("Erro:", error);
  }
});


file.addEventListener("change", function () {
  const previewArea = document.getElementById("preview-area");
  const fileInput = file.files[0];

  if (fileInput && fileInput.type.startsWith("image/")) {
    const reader = new FileReader();

    reader.onload = function (e) {
      previewArea.innerHTML = `<img src="${e.target.result}" alt="Preview" class="preview-img" />`;
    };

    reader.readAsDataURL(fileInput);
  } else {
    previewArea.innerHTML = `
      <svg ...></svg>
      <p>Browse File to upload!</p>
    `;
  }
});

async function mostrarProduto() {
  try {
    const response = await fetch("/admin/home");
    if (!response.ok) throw new Error("Erro ao buscar produtos");

    const produtos = await response.json();
    const resultadoDiv = document.getElementById("resultado");

    resultadoDiv.innerHTML = "";
    produtos.forEach(produto => {
      const productHTML = `
        <div class="produto-item">
          <h3>${produto.name}</h3>
          <p>Valor: R$ ${produto.valueProduct.toFixed(2)}</p>
          <p>${produto.describe}</p>
          <img src="/api/upload/view/${produto.imgName}" 
               alt="${produto.name}" style="width: 200px; height: 200px; object-fit: cover;" />
          
          <div class="card3">
            <ul class="list">
              <li class="element rename" data-id="${produto.id}">
                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="none" stroke="#7e8590" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-pencil">
                  <path d="M21.174 6.812a1 1 0 0 0-3.986-3.987L3.842 16.174a2 2 0 0 0-.5.83l-1.321 4.352a.5.5 0 0 0 .623.622l4.353-1.32a2 2 0 0 0 .83-.497z"></path>
                  <path d="m15 5 4 4"></path>
                </svg>
                <button class="label" onclick="updateProduct()">Rename</button>
              </li>
              <li class="element delete" data-id="${produto.id}">
                <svg class="lucide lucide-trash-2" stroke-linejoin="round" stroke-linecap="round" stroke-width="2" stroke="#7e8590" fill="none" viewBox="0 0 24 24" height="24" width="24">
                  <path d="M3 6h18"></path>
                  <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"></path>
                  <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"></path>
                  <line y2="17" y1="11" x2="10" x1="10"></line>
                  <line y2="17" y1="11" x2="14" x1="14"></line>
                </svg>
                <button class="label delete-btn" onclick="deleteProduct()">Delete</button>
              </li>
            </ul>
          </div>
          <hr>
        </div>
      `;
      resultadoDiv.innerHTML += productHTML;
    });
    

  } catch (error) {
    console.error("Erro ao carregar produtos:", error);
  }
}
