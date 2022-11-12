/* eslint-disable testing-library/no-render-in-setup */
import { queryByPlaceholderText, render , screen} from "@testing-library/react";
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
      const passwordInput = screen.queryByPlaceholderText(/Your password/i);
      expect(passwordInput).toBeInTheDocument();
     })

     it('has password type for password input', () => {
        render(<UserSignUpPage></UserSignUpPage>)
        const passworType = screen.queryByPlaceholderText(/Your password/i);
        expect(passworType).toHaveAttribute('type','password');
     })

     
});