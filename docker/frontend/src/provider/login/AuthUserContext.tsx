import { sha512 } from "js-sha512";
import React, { createContext, useContext, useState } from "react";
import { instance } from "../../api/axios";
import { useMessage } from "../../hooks/useMessage";

type loginArgs = {
  userId: string;
  password: string;
};

type OperationType = {
  login: (object: loginArgs) => void;
  logout: () => void;
  guestLogin: () => void;
};

type AuthUser = {
  userId: string;
  isAdmin: boolean;
};

const AuthUserContext = createContext<AuthUser | null>(null);

const AuthOperationContext = createContext<OperationType>({
  login: () => {},
  logout: () => {},
  guestLogin: () => {},
});

const AuthUserProvider: React.FC = (props) => {
  const { children } = props;
  const [authUser, setAuthUser] = useState<AuthUser | null>(null);
  const { showMessage } = useMessage();

  const login = async (loginArgs: loginArgs) => {
    const hashPass = sha512(loginArgs.userId + loginArgs.password);

    instance
      .get(`/employees/login/${loginArgs.userId}`)
      .then((res) => {
        const { password, authority } = res.data;
        if (password === hashPass) {
          if (authority === "admin")
            setAuthUser({ userId: `${loginArgs.userId}`, isAdmin: true });
          if (authority === "employee")
            setAuthUser({ userId: `${loginArgs.userId}`, isAdmin: false });
          showMessage({
            title: "ログインに成功しました",
            status: "success",
          });
        } else {
          showMessage({
            title: "ログインに失敗しました",
            status: "error",
          });
        }
      })
      .catch(() => {
        showMessage({
          title: "ログイン情報が取得できませんでした",
          status: "error",
        });
      });

    // setAuthUser({ userId: `${loginArgs.userId}`, isAdmin: true });
  };

  const guestLogin = async () => {
    setAuthUser({ userId: "1", isAdmin: true });
    showMessage({
      title: "ログインに成功しました",
      status: "success",
    });
  };

  const logout = async () => {
    setAuthUser(null);
    showMessage({
      title: "ログアウトしました",
      status: "success",
    });
  };

  return (
    <AuthOperationContext.Provider value={{ login, logout, guestLogin }}>
      <AuthUserContext.Provider value={authUser}>
        {children}
      </AuthUserContext.Provider>
    </AuthOperationContext.Provider>
  );
};

export const useAuthUser = () => useContext(AuthUserContext);
export const useLogin = () => useContext(AuthOperationContext).login;
export const useLogout = () => useContext(AuthOperationContext).logout;
export const useGuestLogin = () => useContext(AuthOperationContext).guestLogin;

export default AuthUserProvider;
