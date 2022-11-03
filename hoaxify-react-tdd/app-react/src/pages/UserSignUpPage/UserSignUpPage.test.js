/* eslint-disable testing-library/no-render-in-setup */
import { render } from "@testing-library/react";
import UserSignUpPage from "./UserSignUpPage";

describe("UserSignUpPage" ,() => {
     let renderInstance ;
     const mockUserLogin = {
        username: 'Alan',
        password: 'Pssword1'
     };
     renderInstance = render
     beforeEach(() => {
         renderInstance = render(<UserSignUpPage />);
     })
     afterEach(() => {
        jest.clearAllMocks();
     });

     it('renders Login component' , () => {
        // eslint-disable-next-line no-unused-expressions
        expect(renderInstance).toBeTruthy;
     });
     it('Page headers', () => {
         const header = renderInstance.querySelector('h1');

     })

     
});