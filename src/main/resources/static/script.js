const loginNavBtn = document.getElementById("login-nav");
const signupNavBtn = document.getElementById("signup-nav");
const loginPage = document.getElementById("login-page");
const signupPage = document.getElementById("signup-page");
const loginPageOverlay = document.getElementById("login-page-overlay");
const signupPageOverlay = document.getElementById("signup-page-overlay");
const loginForm = document.getElementById("form-login");
const signupForm = document.getElementById("form-signup");
const username_field = document.getElementById("user-input");
const password_field = document.getElementById("password-input");
const signupUsernameField = document.getElementById("signup-user-input");
const signupPasswordField = document.getElementById("signup-password-input");
const termsConditions_field = document.getElementById("checkbox-input");

let loginPageIsHidden = true;
let signupPageIsHidden = true;

const clearForm = () => {
  username_field.value = "";
  password_field.value = "";
  signupUsernameField.value = "";
  signupPasswordField.value = "";
  termsConditions_field.checked = false;
};
const toggleLoginPage = () => {
  loginPageIsHidden = !loginPageIsHidden;
  loginPage.style.display = loginPageIsHidden ? "none" : "block";
  clearForm();
};
const toggleSignupPage = () => {
  signupPageIsHidden = !signupPageIsHidden;
  signupPage.style.display = signupPageIsHidden ? "none" : "block";
  clearForm();
};

const login = (event) => {
  event.preventDefault();
  // console.log(
  //   `Username : ${username_field.value}, Password : ${password_field.value}`
  // );
  const username = username_field.value;
  const password = password_field.value;
  fetch("http://localhost:8080/login", {
    method: "POST",
    headers: {
      Authorization: `Basic ${btoa(username + ":" + password)}`,
    },
  }).then((response) => {
    if (response.ok) {
      console.log("Login Successfull");
    } else {
      console.error("Login failed");
    }
  });
  clearForm();
};

const signup = (event) => {
  event.preventDefault();
  // console.log(
  //   `Username : ${signupUsernameField.value}, Password : ${signupPasswordField.value}, Terms and Condtions accepted : ${termsConditions_field.checked}`
  // );
  const username_ = signupUsernameField.value;
  const password_ = signupPasswordField.value;
  
  let aaa = JSON.stringify({
    email: username_,
    password: password_,
  });
  console.log(aaa);
  fetch("http://localhost:8080/signup", {
    method: "post",
    body: aaa,
    headers : {
      "Content-Type" : "application/json"
    }
  }).then((response) => {
    if (response.ok) {
      console.log("Signup Success");
    } else {
      console.error("Signup Failed");
    }
  });

  clearForm();
};

loginNavBtn.addEventListener("click", toggleLoginPage);
loginPageOverlay.addEventListener("click", toggleLoginPage);
signupNavBtn.addEventListener("click", toggleSignupPage);
signupPageOverlay.addEventListener("click", toggleSignupPage);
loginForm.addEventListener("submit", login);
signupForm.addEventListener("submit", signup);
