/*LOGIN FUNCTIONALITY*/
function logIn(event) {
  document.querySelector('.signupBar').style.display = "none";
    event.preventDefault();
    const email = document.querySelector('.email');
    const password = document.querySelector('.pw');
    const username = document.querySelector('.username');

    fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email.value,
                password: password.value,
                username: username.value
            })
    })
    .then((res) => {
        return res.json();
    })
    .then((res) => {
        localStorage.setItem('user', res.token);
    })
      .then((res) => {
    createPost();
    })
      .then((res) => {
    createComment();
    })
      .then((res)=>{
    deletePost();
    })
      .then((res)=>{
    deleteComment();
    })
      .then((res)=>{
    profile();
    })
      .then((res)=>{
    console.log(res.token);
    })
      .catch((err) => {
    console.log(err);
    })
}

/*SIGNUP functionality*/

function signUp(event) {
  document.querySelector('.signupBar').style.display = "none";
    event.preventDefault();
    const email = document.querySelector('.email');
    const password = document.querySelector('.pw');
    const username = document.querySelector('.username');

    fetch('http://localhost:8080/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email.value,
                password: password.value,
                username: username.value
            })
    })
    .then((res) => {
        return res.json();
    })
    .then((res) => {
        localStorage.setItem('user', + res.token);
    })
    .catch((err) => {
        console.log(err);
    })
}

//ADDING PROFILE UPDATE FUNCTIONALITY//

function profile(event) {
    event.preventDefault();
    document.querySelector('.userContainer').style.display = "none";
    const bio = document.querySelector('.bio');
    const location = document.querySelector('.location');
    const mobile = document.querySelector('.mobile');

    fetch('http://localhost:8080/profile/{username}', {
            method: 'POST',
            headers: {
              "Authorization": "Bearer " + localStorage.getItem('user'),
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
                bio: bio.value,
                location: location.value,
                mobile: mobile.value
            })
    })
    .then((res) => {
    return res.json();
    })
    .catch((err) => {
        console.log(err);
    })
}

//listAllPosts will populate post Wall.//

function listAllPosts(event) {

  document.querySelector('.allPosts').style.display = "block";
  fetch("http://localhost:8080/{username}/post/list", {
          method: "GET",
          headers: {
                "Authorization": "Bearer " + localStorage.getItem('user'),
                "Content-Type": "application/json"
            }
        })
        .then((res) => {
            return res.json();
        })
        .then((res) =>{
          const list = document.querySelector('.allPosts');
          for (let i = 0; i < res.length; i++) {

            const item = document.createElement('li');
            const title = document.createElement('h3');
            const body = document.createElement('p');
            const commentInput =
            document.createElement('input');
            const buttonDelete = document.createElement('input');

            item.appendChild(title);
            item.appendChild(body);

            title.innerText = res[i].title;
            body.innerText = res[i].body;

            list.appendChild(item);
            list.appendChild(commentInput);
            list.appendChild(buttonDelete);

            commentInput.setAttribute("class", "comment");
            commentInput.setAttribute("type", "submit");
            commentInput.setAttribute("value", "comment");
            commentInput.setAttribute("onclick", "createComment(event)");

            buttonDelete.setAttribute("class", "delete");
            buttonDelete.setAttribute("type", "submit");
            buttonDelete.setAttribute("value", "delete");
            buttonDelete.setAttribute("onclick", "deletePost(event)");

            item.setAttribute("class", "userPost");
          }
  })
}
listAllPosts();

//CREATE A POST FUNCTIONALITY//

function createPost(event) {
    event.preventDefault();
    const title = document.querySelector('.title');
    const body = document.querySelector('.body');

    fetch("http://localhost:8080/{username}/post", {
        method: 'POST',
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('user'),
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            title: title.value,
            body: body.value
        })
    })
    .then((res) => {
        return res.json();
    })
    .then((res) => {
        return console.log(res);
        listAllPosts(res);
    })
    .catch((err) => {
        console.log(err);
    })
}


//CREATE COMMENT FUNCTIONALITY//
function createComment(event) {
  document.querySelector(".commentInput");
  document.querySelector(".userPost").style.display = "block";
  event.preventDefault();
  fetch("http://localhost:8080/{username}/{postId}/comment", {
      method: 'POST',
      headers: {
          "Authorization": "Bearer " + localStorage.getItem('user'),
          "Content-Type": "application/json"
      },
      body: JSON.stringify({
          title: title.value,
          body: body.value
      })
  })
  .then((res) => {
      return res.json();
  })
  .then((res) => {
      console.log(res);
      listAllPosts(res);
  })
    .then((res) => {
        const commentList =
        document.querySelector('.comment');
        for (let i = 0; i < res.length; i++) {
          const commentContainer = document.createElement('ul');
          const commentItem = document.createElement('li');
          const commentTitle = document.createElement('h3');
          const commentBody = document.createElement('p');
          const commentDelete = document.createElement('input');

          commentTitle.innerText = res[i].commentTitle;
          commentBody.innerText = res[i].commentBody;
          commentContainer.appendChild(commentItem);
          commentItem.appendChild(commentTitle);
          commentItem.appendChild(commentBody);

          commentContainer.setAttribute("class", "commentContainer");
          commentItem.setAttribute("class", "commmentItem");
          commentTitle.setAttribute("class", "commentTitle");
          commentBody.setAttribute("class", "commentBody");

          commentDelete.setAttribute("class", "delete");
          commentDelete.setAttribute("type", "submit");
          commentDelete.setAttribute("value", "deleteComment");
          commentDelete.setAttribute("onclick", "deleteComment(event)");
        }
      })
        .catch((err) => {
            console.log(err);
      })
    }

//DELETE POST FUNCTIONALITY//

function deletePost(event) {
  event.preventDefault();
  document.querySelector(".delete");
  document.querySelector('.userPost', '.buttonDelete', 'commentInput').style.display = "none";
  fetch('http://localhost:8080/{username}/post/delete/{postId}', {
      method: "DELETE",
      headers: {
          "Authorization": "Bearer" +
          localStorage.getItem('user'),
          "Content-Type": "application/json"
        },
      })
      .then((res) => {
          console.log(res);
      })
      .catch((err) => {
          console.log(err);
      })
      .then((res) => {

      })
}

//DELETE COMMENT FUNCTIONALITY//

function deleteComment(event) {
  event.preventDefault();
  document.querySelector(".deleteComment");

  fetch('http://localhost:8080/{username}/comment/delete/{commentId}', {
      method: "DELETE",
      headers: {
          "Authorization": "Bearer" +
          localStorage.getItem('user'),
          "Content-Type": "application/json"
        },
      })
      .then((res) => {
          console.log(res);
      })
      .catch((err) => {
          console.log(err);
      })
      .then((res) => {
      })
}
