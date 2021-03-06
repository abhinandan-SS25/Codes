document.addEventListener('DOMContentLoaded', function() {

  // Use buttons to toggle between views
  document.querySelector('#inbox').addEventListener('click', () => load_mailbox('inbox'));
  document.querySelector('#sent').addEventListener('click', () => load_mailbox('sent'));
  document.querySelector('#archived').addEventListener('click', () => load_mailbox('archive'));
  document.querySelector('#compose').addEventListener('click', compose_email);

  // By default, load the inbox
  load_mailbox('inbox');

  
});

function compose_email() {

  // Show compose view and hide other views
  document.querySelector('#emails-view').style.display = 'none';
  document.querySelector('#compose-view').style.display = 'block';
  document.querySelector("#viewmail").style.display = "none";


  // Clear out composition fields
  document.querySelector('#compose-recipients').value = '';
  document.querySelector('#compose-subject').value = '';
  document.querySelector('#compose-body').value = '';


}

function load_mailbox(mailbox) {
  
  // Show the mailbox and hide other views
  document.querySelector('#emails-view').style.display = 'block';
  document.querySelector('#compose-view').style.display = 'none';
  document.querySelector("#viewmail").style.display = "none";


  // Show the mailbox name
  document.querySelector('#emails-view').innerHTML = `<h3>${mailbox.charAt(0).toUpperCase() + mailbox.slice(1)}</h3>`;

  if (mailbox==="inbox") {
  fetch(`/emails/${mailbox}`)
  .then(response => response.json())
  .then(emails => {

    emails.forEach( function(mail) {
      var newmail = document.createElement("div");
      let s_body=mail["body"].slice(0,80);

      if (mail.read){
      newmail.innerHTML=`
      <div class="btn btn-light" onclick="view_email_inbox(${mail.id})" id="${mail.id}" style="border-radius:5px; padding:4px;">
      <div style="text-align:left;">
        <strong>${mail.sender}</strong>
      </div>
      <div style="text-align:center; color:rgb(68, 65, 65); width:1200px;">
      ${s_body}...
      </div>
      <div style="text-align:right">
      <b>${mail.timestamp}</b>
      </div>
      </div>
      `;
      document.querySelector("#emails-view").append(newmail);}

      else {
        newmail.innerHTML=`
        <div class="btn btn-dark" onclick="view_email_inbox(${mail.id})" id="${mail.id}" style="border-radius:5px; padding:4px;">
        <div style="text-align:left;">
          <strong>${mail.sender}</strong>
        </div>
        <div style="text-align:center; color:rgb(253, 253, 253); width:1200px;">
        ${s_body}...
        </div>
        <div style="text-align:right">
        <b>${mail.timestamp}</b>
        </div>
        </div>
      `;
      document.querySelector("#emails-view").append(newmail);}
   });
  });
  }

  else if (mailbox==="sent"){
  fetch(`/emails/${mailbox}`)
  .then(response => response.json())
  .then(emails => {

    emails.forEach( function(mail) {
      var newmail = document.createElement("div");
      let s_body=mail["body"].slice(0,80);

      if (mail.read){
      newmail.innerHTML=`
      <div class="btn btn-light" onclick="view_email_sent(${mail.id})" id="${mail.id}" style="border-radius:5px; padding:4px;">
      <div style="text-align:left;">
        TO: <strong>${mail.recipients}</strong>
      </div>
      <div style="text-align:center; color:rgb(68, 65, 65); width:1200px;">
      ${s_body}...
      </div>
      <div style="text-align:right">
      <b>${mail.timestamp}</b>
      </div>
      </div>
      `;
      document.querySelector("#emails-view").append(newmail);}

      else {
        newmail.innerHTML=`
        <div class="btn btn-dark" onclick="view_email_sent(${mail.id})" id="${mail.id}" style="border-radius:5px; padding:4px;">
        <div style="text-align:left;">
          <strong>${mail.sender}</strong>
        </div>
        <div style="text-align:center; color:rgb(253, 253, 253); width:1200px;">
        ${s_body}...
        </div>
        <div style="text-align:right">
        <b>${mail.timestamp}</b>
        </div>
        </div>
      `;
      document.querySelector("#emails-view").append(newmail);}
   });
  });
  }

  else {
    fetch(`/emails/${mailbox}`)
    .then(response => response.json())
    .then(emails => {
  
      emails.forEach( function(mail) {
        var newmail = document.createElement("div");
        let s_body=mail["body"].slice(0,80);
  
        if (mail.read){
        newmail.innerHTML=`
        <div class="btn btn-light" onclick="view_email_archive(${mail.id})" id="${mail.id}" style="border-radius:5px; padding:4px;">
        <div style="text-align:left;">
          <strong>${mail.sender}</strong> to <strong>${mail.recipients}</strong>
        </div>
        <div style="text-align:center; color:rgb(68, 65, 65); width:1200px;">
        ${s_body}...
        </div>
        <div style="text-align:right">
        <b>${mail.timestamp}</b>
        </div>
        </div>
        `;
        document.querySelector("#emails-view").append(newmail);}
  
        else {
          newmail.innerHTML=`
          <div class="btn btn-dark" onclick="view_email_archive(${mail.id})" id="${mail.id}" style="border-radius:5px; padding:4px;">
          <div style="text-align:left;">
            <strong>${mail.sender}</strong> to <strong>${mail.recipients}</strong>
          </div>
          <div style="text-align:center; color:rgb(253, 253, 253); width:1200px;">
          ${s_body}...
          </div>
          <div style="text-align:right">
          <b>${mail.timestamp}</b>
          </div>
          </div>
        `;
        document.querySelector("#emails-view").append(newmail);}
     });
    });
    }


}

function hello () {

  fetch('/emails', {
    method: 'POST',
    body: JSON.stringify({
      recipients: document.querySelector("#compose-recipients").value,
      subject: document.querySelector("#compose-subject").value,
      body: document.querySelector("#compose-body").value
    })
  })
  .then(response => response.json())
  .then(response => {
      // Print result
      console.log(response);

      if (response.error) {
        alert(response.error);
        compose_email();
      }
  });

}


function view_email_inbox(id) {
  fetch(`/emails/${id}`)
  .then(response => response.json())
  .then(email => {
    document.querySelector('#emails-view').style.display = 'none';
    document.querySelector('#compose-view').style.display = 'none';
    document.querySelector("#viewmail").style.display = "block";
    document.querySelector("#viewmail").innerHTML = "";

    var viewemail = document.createElement("div");
    viewemail.innerHTML=`
    <hr>
    <p><b>From:</b> ${email.sender}</p>
    <p><b>To:</b> ${email.recipients}</P>
    <p><b>Time Stamp:</b> ${email.timestamp}</P>
    <p><b>Subject:</b> <strong><i>${email.subject}</i></strong></P>
    <p><button class="btn btn-outline-warning" onclick="reply(${email.id})" id="reply">Reply to ${email.sender}</button><button class="btn btn-outline-success" onclick="archivemail(${email.id})" id="${email.id}">Archive This Mail</button></p>
    <hr>
    <div style="white-space:initial">${email.body}</div>
    `;
    document.querySelector("#viewmail").append(viewemail);
  })
  .then(
    fetch(`/emails/${id}`, {
    method: 'PUT',
    body: JSON.stringify({
        read: true
    })
  }));

}


function view_email_sent(id) {
  fetch(`/emails/${id}`)
  .then(response => response.json())
  .then(email => {
    document.querySelector('#emails-view').style.display = 'none';
    document.querySelector('#compose-view').style.display = 'none';
    document.querySelector("#viewmail").style.display = "block";
    document.querySelector("#viewmail").innerHTML = "";

    var viewemail = document.createElement("div");
    viewemail.innerHTML=`
    <hr>
    <p><b>From:</b> ${email.sender}</p>
    <p><b>To:</b> ${email.recipients}</P>
    <p><b>Time Stamp:</b> ${email.timestamp}</P>
    <p><b>Subject:</b> <strong><i>${email.subject}</i></strong></P>
    <p><button class="btn btn-outline-warning" onclick="reply(${email.recipients},${email.subject},${email.body},${email.timestamp},${email.sender})" id="reply">Reply to ${email.sender}</button></p>
    <hr>
    <div style="white-space:initial">${email.body}</div>
    `;
    document.querySelector("#viewmail").append(viewemail);
  })
  .then(
    fetch(`/emails/${id}`, {
    method: 'PUT',
    body: JSON.stringify({
        read: true
    })
  }));

}


function view_email_archive(id) {
  fetch(`/emails/${id}`)
  .then(response => response.json())
  .then(email => {
    document.querySelector('#emails-view').style.display = 'none';
    document.querySelector('#compose-view').style.display = 'none';
    document.querySelector("#viewmail").style.display = "block";
    document.querySelector("#viewmail").innerHTML = "";

    var viewemail = document.createElement("div");
    viewemail.innerHTML=`
    <hr>
    <p><b>From:</b> ${email.sender}</p>
    <p><b>To:</b> ${email.recipients}</P>
    <p><b>Time Stamp:</b> ${email.timestamp}</P>
    <p><b>Subject:</b> <strong><i>${email.subject}</i></strong></P>
    <p><button class="btn btn-outline-warning" onclick="reply(${email.recipients},${email.subject},${email.body},${email.timestamp},${email.sender})" id="reply">Reply to ${email.sender}</button><button class="btn btn-outline-success" onclick="unarchivemail(${email.id})" id="${email.id}">Unarchive This Mail</button></p>
    <hr>
    <div style="white-space:initial">${email.body}</div>
    `;
    document.querySelector("#viewmail").append(viewemail);
  })
  .then(
    fetch(`/emails/${id}`, {
    method: 'PUT',
    body: JSON.stringify({
        read: true
    })
  }));

}


function archivemail (id) {
  fetch(`/emails/${id}`, {
    method: 'PUT',
    body: JSON.stringify({
        archived: true
    })
  }).then(load_mailbox("inbox"));
}


function unarchivemail (id) {
  fetch(`/emails/${id}`, {
    method: 'PUT',
    body: JSON.stringify({
        archived: false
    })
  }).then(load_mailbox("inbox"));
}


function reply (id) {
  document.querySelector('#emails-view').style.display = 'none';
  document.querySelector('#compose-view').style.display = 'block';
  document.querySelector("#viewmail").style.display = "none";
  
  fetch(`/emails/${id}`)
  .then(response => response.json())
  .then(email => {
    document.querySelector('#compose-recipients').value = `${email.recipients}`;
    document.querySelector('#compose-subject').value = `Re: ${email.subject}`;
    document.querySelector('#compose-body').value = `On ${email.timestamp}, ${email.sender} wrote: ${email.body}`
});
}