document.querySelector("form").addEventListener("submit", async function (e) {
    e.preventDefault();
  
    const productData = {
      name: this.name.value,
      valueProduct: parseFloat(valueProduct.value),
      describe: describe.value
    };
    
    try {
      const response = await fetch("/admin/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(productData)
      });

      uploadFiles();
      if (response.ok) {
        alert("Produto cadastrado com sucesso!!");
      } else {
        alert("Ocorreu um erro, tente novamente.");
      }
    } catch (error) {
      console.error("Erro:", error);
    }

    async function uploadFiles() {
      const file = document.getElementById("file");
      const img = file.files[0];
    
      if (!img) {
        alert("Selecione uma imagem antes de enviar.");
        return;
      }
    
      const formData = new FormData();
      formData.append("file", img);
    
      try {
        const response = await fetch("/api/upload", {
          method: "POST",
          body: formData
        });
    
        if (response.ok) {
          alert("Imagem enviada com sucesso!");
        } else {
          alert("Erro ao enviar imagem, tente novamente.");
        }
      } catch (error) {
        console.error("Error Image:", error);
      }
    }    
});
  