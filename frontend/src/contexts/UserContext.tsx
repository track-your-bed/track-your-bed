import * as React from "react";

/**
 * NOTE:
 * This is still work in progress.
 * - get rid of all the "any" typescript declarations
 * - once auth-backend is known and online, refine the process in this file
 */

const TOKEN_NAME = "tyb-jwt";

interface UserContextProvider {
  children: React.ReactChild;
}

const initialState = {
  user: {
    name: ""
  },
  isAuthenticated: false
};

export const UserContext = React.createContext(initialState);

const reducer = (state: any, action: any): any => {
  switch (action.type) {
    case "loginUser":
      localStorage.setItem(TOKEN_NAME, "true");
      return {
        ...state,
        user: {
          name: action.payload.name
        },
        isAuthenticated: true
      };

    case "logoutUser":
      localStorage.removeItem(TOKEN_NAME);
      return {
        ...state,
        user: {
          name: ""
        },
        isAuthenticated: false
      };

    default:
      return state;
  }
};

const UserContextProvider: React.FunctionComponent<UserContextProvider> = ({
  children
}: UserContextProvider) => {
  const [state, dispatch] = React.useReducer(reducer, initialState);

  const login = (name: string) =>
    dispatch({ type: "loginUser", payload: { name } });

  const logout = () => dispatch({ type: "logoutUser" });

  React.useEffect(() => {
    if (localStorage.getItem(TOKEN_NAME) && !state.user.isAuthenticated) {
      login("defaultUser");
    }
  }, []);

  return (
    <UserContext.Provider
      value={{
        ...state,
        login,
        logout
      }}
    >
      {children}
    </UserContext.Provider>
  );
};

export default UserContextProvider;
