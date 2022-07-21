import { memo, VFC } from "react";
import { Redirect, Route, Switch } from "react-router-dom";
import { Login } from "../components/pages/Login";
import { Logout } from "../components/pages/Logout";

import { HeaderLayout } from "../components/templates/HeaderLayout";
import { employeesRoutes } from "./employeesRoutes";
import { PrivateRoute } from "./login/PrivateRoute";
import { UnAuthRoute } from "./login/UnAuthRoute";
import { shiftRoutes } from "./shiftRoutes";

export const Router: VFC = memo(() => {
  return (
    <Switch>
      <UnAuthRoute exact path="/login" component={Login} />
      <Route exact path="/logout" component={Logout} />
      <Route path="/shift/draft/redirect">
        <Redirect to="/shift/draft" />
      </Route>
      <Route path="/employees/redirect">
        <Redirect to="/employees" />
      </Route>
      <PrivateRoute
        path="/shift"
        render={({ match: { url } }) => (
          <Switch>
            {shiftRoutes.map((route) => (
              <Route
                exact={route.exact}
                path={`${url}${route.path}`}
                key={route.path}
              >
                <HeaderLayout>{route.children}</HeaderLayout>
              </Route>
            ))}
          </Switch>
        )}
      />
      <PrivateRoute
        path="/employees"
        render={({ match: { url } }) => (
          <Switch>
            {employeesRoutes.map((route) => (
              <Route
                key={route.path}
                exact={route.exact}
                path={`${url}${route.path}`}
              >
                <HeaderLayout>{route.children}</HeaderLayout>
              </Route>
            ))}
          </Switch>
        )}
      />
      <Route path="*">
        <Redirect to="/shift" />
      </Route>
    </Switch>
  );
});
