import React from 'react';
export interface UserSignUpPageInterface {}

const UserSignUpPage : React.FC<UserSignUpPageInterface> = () => {
	
	const [displaName, setDisplayName]= React.useState("my-display-name");
	const [username, setUserName] = React.useState("my-username");
	const [password, setPassword] = React.useState("my-password");
	const [passwordRepeat, setPasswordRepeat] = React.useState("my-password-repeat");


	const onClickSignUp = () => {
       
	}

	return ( <>
	      <h1>Sign User</h1>
		  <input type="text" data-testid="test-displayname" value={displaName} 
		   onChange={(e) => setDisplayName(e.target.value)} 
		  placeholder='Your display name'></input>
		  {displaName}
		  <input type="text" 
		   value={username}
		   onChange={(e) => setUserName(e.target.value)} 
		   data-testid="test-username" placeholder='Your username'></input>
		  <input  placeholder='Your password' type="password" data-testid="test-password" value={password} onChange={(e) =>  setPassword(e.target.value)}></input>
		  <input  placeholder='Repeat your password' type="password" data-testid="test-repeat-password" value={passwordRepeat} onChange={(e) => setPasswordRepeat(e.target.value)}></input>
		 
		  <button type="submit" data-testid="btn-enviar" onClick={onClickSignUp}>Enviar</button>
		 </>);
};

export default UserSignUpPage;
