import { Redirect, Route, RouteProps } from "react-router-dom";
import { useAuthUser } from "../../provider/login/AuthUserContext";

export const PrivateRoute: React.FC<RouteProps> = ({ ...props }) => {
  const authUser = useAuthUser();
  const isAuthenticated = authUser != null;

  if (isAuthenticated) {
    return <Route {...props} />;
  } else {
    console.log(
      `ログインしていないユーザーは${props.path}へはアクセスできません`
    );

    return (
      <Redirect
        to={{
          pathname: "/login",
          state: { from: props.location?.pathname },
        }}
      />
    );
  }
};
