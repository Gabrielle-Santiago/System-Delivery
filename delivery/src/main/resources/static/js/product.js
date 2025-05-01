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
          <hr>
        </div>
      `;
      resultadoDiv.innerHTML += productHTML;
    });

  } catch (error) {
    console.error("Erro ao carregar produtos:", error);
  }
}
