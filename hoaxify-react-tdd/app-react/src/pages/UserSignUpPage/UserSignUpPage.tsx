import React from 'react';
export interface UserSignUpPageInterface {}

const UserSignUpPage : React.FC<UserSignUpPageInterface> = () => {
	return ( <>
	      <h1>Sign User</h1>
		  <input type="text" placeholder='Your display name'></input>
		  <input type="text" placeholder='Your username'></input>
		  <input  placeholder='Your password' type="password"></input>
		  <input  placeholder='Repeat your password' type="password"></input>
		  <button type="submit">Enviar</button>
		 </>);
};

export default UserSignUpPage;
