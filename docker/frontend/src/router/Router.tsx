import { memo, VFC } from "react";
import { Route, Switch } from "react-router-dom";
import { Login } from "../components/pages/Login";
import { Page404 } from "../components/pages/Page404";
import { HeaderLayout } from "../components/templates/HeaderLayout";
import { employeesRoutes } from "./employeesRoutes";
import { shiftRoutes } from "./shiftRoutes";

export const Router: VFC = memo(() => {
  return (
    <Switch>
      <Route exact path="/shiftwork_management/login">
        <Login />
      </Route>
      <Route
        path="/shiftwork_management/shift"
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
      <Route
        path="/shiftwork_management/employees"
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
        <Page404 />
      </Route>
    </Switch>
  );
});
