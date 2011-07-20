require "sinatra"
require 'json'

configure do
  set :port, 4999
end

helpers do
  def protected!
    unless authorized?
      response['WWW-Authenticate'] = %(Basic realm="Restricted Area")
      throw(:halt, [401, "Not authorized\n"])
    end
  end
  
  def authorized?
    @auth ||=  Rack::Auth::Basic::Request.new(request.env)
    @auth.provided? && @auth.basic? && @auth.credentials && @auth.credentials == ['manager', 'secret']
  end
end

before do
  auth_path = ['/signin.json']
  protected! if (auth_path.include?(request.path_info))
end

get "/hello" do
  "world"
end

get "/username" do
  puts request.env
  if params[:id] == '1'
    "Jack"
  else
    "Null"
  end
end

get "/users.json" do
  if params[:id] == '1'
    {:id => 1, :username => 'jack'}.to_json
  else
    "Null"
  end
end

get "/signin.json" do
  {:id => 1, :username => 'jack'}.to_json
end

post "/hello" do
  "bye"
end

post "/try_pic" do
  puts params
  "ok"
end

post "/signup.json" do
  {:id => 1, :username => params[:username]}.to_json
end