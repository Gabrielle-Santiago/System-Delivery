document.getElementById("formRegister").addEventListener("submit", async function (e) {
  e.preventDefault();

  const camposInput = document.getElementsByClassName("required");
  const spans = document.getElementsByClassName("span-required");

  const data = {
    login: this.login.value,
    password: this.password.value,
    email: this.email.value,
    role: this.role.value,
  };

  nameValidate();
  emailValidate();
  passwordValidate();
  roleValidate();

  const hasError = Array.from(spans).some(span => span.style.display === "block");
  if (hasError) return;

  try {
    const response = await fetch("/auth/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data)
    });

    if (response.ok) {
      alert("Cadastro realizado com sucesso!");
      window.location.href = "/login";
    } else {
      alert("Erro ao cadastrar.");
    }

  } catch (error) {
    console.error("Erro na requisição:", error);
  }

  function setError(index, message) {
    camposInput[index].style.border = "2px solid #e63636";
    spans[index].style.color = " #e63636";
    spans[index].style.display = "block";
    spans[index].textContent = message;
  }

  function clearError(index) {
    camposInput[index].style.border = "";
    spans[index].style.display = "none";
    spans[index].textContent = "";
  }

  function nameValidate() {
    if (camposInput[0].value.trim().length < 5) {
      setError(0, "Por favor, escreva seu nome completo");
    } else {
      clearError(0);
    }
  }

  function emailValidate() {
    const emailRegex = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}(?:\.[a-z]{2,})?$/i;
    if (emailRegex.test(camposInput[1].value)) {
      clearError(1);
    } else {
      setError(1, "Por favor, acrescente um email válido");
    }
  }

  function passwordValidate() {
    if (camposInput[2].value.trim().length < 6) {
      setError(2, "A senha deve ter no mínimo 6 caracteres");
    } else {
      clearError(2);
    }
  }

  function roleValidate() {
    const role = camposInput[3].value.trim().toUpperCase();
    if (role !== "ADMIN" && role !== "USER") {
      setError(3, "Role deve ser 'ADMIN' ou 'USER'");
    } else {
      clearError(3);
    }
  }
});
