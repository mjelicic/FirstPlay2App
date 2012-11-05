package controllers

import play.api.data.Form
import play.api.data.Forms.{single, nonEmptyText}
import play.api.mvc.{Action, Controller}
import anorm.NotAssigned
import models.Bar
import com.codahale.jerkson.Json

object Application extends Controller {


  val barForm = Form(
    single("name" -> nonEmptyText)
  )

  def index = Action {
    Ok(views.html.index(barForm))
  }

  def addBar() = Action { implicit request =>
    barForm.bindFromRequest.fold(
    errors => BadRequest,
    {
      case (name) =>
        Bar.create(Bar(NotAssigned, name))
        Redirect(routes.Application.index())
    }
    )
  }

  def listBars () = Action {

    val bars = Bar.findAll()
    val jsonResponse = Json.generate(bars)

    Ok(jsonResponse).as("application/json")

  }

  
}