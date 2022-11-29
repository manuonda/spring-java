/* eslint-disable testing-library/no-render-in-setup */
import { fireEvent, queryByPlaceholderText, render , screen} from "@testing-library/react";
import UserSignUpPage from "./UserSignUpPage";

describe("UserSignUpPage" ,() => {
     let renderInstance = null ;
     const mockUserLogin = {
        username: 'Alan',
        password: 'Pssword1'
     };
    
   //   beforeEach(() => {
   //       renderInstance = render(<UserSignUpPage />);
   //   })
     afterEach(() => {
        jest.clearAllMocks();
     });

   //   it('renders Login component' , () => {
   //      // eslint-disable-next-line no-unused-expressions
   //      expect(renderInstance).toBeTruthy;
   //   });
     it('Page headers', () => {
         render(<UserSignUpPage />);
         const header =   screen.getByText(/Sign User/i);
         expect(header).toBeInTheDocument();
     })

     it('has input for display name' ,() => {
        render(<UserSignUpPage></UserSignUpPage>)
        const displayNameInput = screen.queryByPlaceholderText('Your display name');
        expect(displayNameInput).toBeInTheDocument();
     })

     it('has input for username',  () => {
        render(<UserSignUpPage></UserSignUpPage>) 
        const usernameInput = screen.queryByPlaceholderText('Your username') 
        expect(usernameInput).toBeInTheDocument();
     });

     it('has input password', () => {
      render(<UserSignUpPage></UserSignUpPage>)
      const passwordInput = screen.queryByPlaceholderText(/Your password/);
      expect(passwordInput).toBeInTheDocument();
     })

    
     it('hast input for password repeat' ,() => {
      render(<UserSignUpPage />)
      const passwordRepeatType = screen.queryByPlaceholderText(/Repeat your password/);
      expect(passwordRepeatType).toBeInTheDocument();
   })

     it('hast input for password repeat input' ,() => {
        render(<UserSignUpPage />)
        const passwordRepeatType = screen.queryByPlaceholderText(/Repeat your password/);
        expect(passwordRepeatType).toHaveAttribute('type','password');
     })

     it('has submit button' , () => {
          render(<UserSignUpPage></UserSignUpPage>)
          const button = screen.queryByRole('button');
          expect(button).toBeInTheDocument();
     })
  
});

describe('interactions', () => {
  
    const changeEvent = (content:any) => {
       return  {
         target : {
            value:content
         }
       }
    }

    it('sets the displayName value into state' ,() => {
       render(<UserSignUpPage />)
       const displayNameInput = screen.getByTestId("test-displayname");
       //console.log(displayNameInput);
       fireEvent.change(displayNameInput, changeEvent);
       expect(displayNameInput).toHaveValue('my-display-name');
    })

    it('sets the userName value into state' ,() => {
      render(<UserSignUpPage />)
      const userNameInput = screen.getByTestId("test-username");
      //console.log(userNameInput);
      fireEvent.change(userNameInput, changeEvent);
      expect(userNameInput).toHaveValue('my-username');
   })

   it('sets the password value into state' ,() => {
      render(<UserSignUpPage />)
      const passwordInput = screen.getByTestId("test-password");
      //console.log(passwordInput);
      fireEvent.change(passwordInput, changeEvent);
      expect(passwordInput).toHaveValue('my-password');
   })

   it('sets the repeatPassword value into state' ,() => {
      render(<UserSignUpPage />)
      const repeatPasswordInput = screen.getByTestId("test-repeat-password");
      //console.log(repeatPasswordInput);
      fireEvent.change(repeatPasswordInput, changeEvent);
      expect(repeatPasswordInput).toHaveValue('my-password-repeat');
   })

   it('calls postSignUp when the fields are valid and the actions are provided in props ', () => {
       
       const actions = {
         postSignUp : jest.fn().mockResolvedValueOnce({}) 
       }


       render(<UserSignUpPage actions={actions}></UserSignUpPage>)

       const displayNameInput = screen.getByTestId('test-displayname');
       const displayYourUsername = screen.getByTestId('test-username');
       const displayYourPassword = screen.getByTestId("test-password");
       const displayRepeatYourPassword = screen.getByTestId("test-repeat-password");

       fireEvent.change(displayNameInput, changeEvent('my-display-name'));
       fireEvent.change(displayYourUsername, changeEvent('my-display-name'));
       fireEvent.change(displayYourPassword, changeEvent('my-display-name'));
       fireEvent.change(displayRepeatYourPassword, changeEvent('my-display-name'));

       const button = screen.getByTestId('btn-enviar');
       fireEvent.click(button)
       expect(actions.postSignUp).toHaveBeenCalledTimes(1);
        
   });

});