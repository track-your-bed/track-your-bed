import * as React from "react";

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
      localStorage.setItem("jwt", "true");
      return {
        ...state,
        user: {
          name: action.payload.name
        },
        isAuthenticated: true
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

  React.useEffect(() => {
    console.log("hi");
    if (localStorage.getItem("jwt") && !state.user.isAuthenticated) {
      login("defaultUser");
    }
  }, []);

  return (
    <UserContext.Provider
      value={{
        ...state,
        login
      }}
    >
      {children}
    </UserContext.Provider>
  );
};

export default UserContextProvider;
