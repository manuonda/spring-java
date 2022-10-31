import React from 'react'
import '@testing-library/jest-dom/extend-expect'
import {render } from '@testing-library/react'
import UserSingupPage from './UserSingupPage';

describe('UserSignUp Page' , () => {
    let renderInstance;
      
    const mockUser = {
        username:'dgarcia',
        password: 'prueba'
    }

    beforeEach(() => {
        renderInstance = render(<UserSingupPage/>)
    })

    afterEach(jest.clearAllMocks);

    it('Render Page SignUp Page', () => {
        expect(renderInstance).toBeTruthy;
        
    })
});