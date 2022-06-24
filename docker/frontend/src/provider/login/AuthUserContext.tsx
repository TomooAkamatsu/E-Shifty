import React, { createContext, useContext, useState } from "react";

type OperationType = {
  login: (userId: string) => void;
  logout: () => void;
};
type AuthUser = {
  userId: string;
};

const AuthUserContext = createContext<AuthUser | null>(null);

const AuthOperationContext = createContext<OperationType>({
  login: (_) => console.error("Providerが設定されていません"),
  logout: () => console.error("Providerが設定されていません"),
});

const AuthUserProvider: React.FC = (props) => {
  const { children } = props;
  const [authUser, setAuthUser] = useState<AuthUser | null>(null);

  const authUserArr = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];
  const login = async (userId: string) => {
    if (authUserArr.includes(userId)) setAuthUser({ userId: `${userId}` });
  };

  const logout = async () => {
    setAuthUser(null);
  };

  return (
    <AuthOperationContext.Provider value={{ login, logout }}>
      <AuthUserContext.Provider value={authUser}>
        {children}
      </AuthUserContext.Provider>
    </AuthOperationContext.Provider>
  );
};

export const useAuthUser = () => useContext(AuthUserContext);
export const useLogin = () => useContext(AuthOperationContext).login;
export const useLogout = () => useContext(AuthOperationContext).logout;

export default AuthUserProvider;
