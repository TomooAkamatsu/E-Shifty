import { Redirect, Route, RouteProps } from "react-router-dom";
import { useAuthUser } from "../../provider/login/AuthUserContext";

export const UnAuthRoute: React.FC<RouteProps> = ({ ...props }) => {
  const authUser = useAuthUser();
  const isAuthenticated = authUser != null;

  if (isAuthenticated) {
    console.log(`ログイン済みのユーザーは${props.path}へはアクセスできません`);
    return <Redirect to={"/shiftwork_management/shift"} />;
  } else {
    return <Route {...props} />;
  }
};
