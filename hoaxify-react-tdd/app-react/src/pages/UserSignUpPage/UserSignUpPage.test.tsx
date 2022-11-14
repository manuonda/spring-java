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
  
    it('sets the displayName value into state' ,() => {
       render(<UserSignUpPage />)
       const displayNameInput = screen.queryByPlaceholderText(/Your display name/);
       const changeEvent = {
          target : {
            value : 'my-display-name'
          }
       }
       fireEvent.change(displayNameInput, changeEvent);
       expect(displayNameInput).toHaveValue('my-display-name');
    })

});